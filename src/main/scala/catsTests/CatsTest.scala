import cats.effect.{IO, Resource}
import cats.syntax.all._
import com.sun.management.VMOption.Origin

import java.io._
import java.io.File
import javax.print.attribute.standard.Destination

def inputStream(file: File): Resource[IO, FileInputStream] =
  Resource.make {
    IO(new FileInputStream(file))
  } { inStream =>
    IO(inStream.close()).handleErrorWith(_ => IO.unit) // TODO add logging
  }

def outputStream(file: File): Resource[IO, FileOutputStream] =
  Resource.make {
    IO( new FileOutputStream(file))
  } { outputStream =>
    IO(outputStream.close()).handleErrorWith(_ => IO.unit) // TODO add logging
  }

def inputOutputStreams(input: File, output: File): Resource[IO, (InputStream, OutputStream)] =
  for {
    inStream <- inputStream(input)
    outStream <- outputStream(output)
  } yield (inStream, outStream)

def transmit(origin: InputStream, destination: OutputStream,buffer: Array[Byte], acc: Long): IO[Long] =
  for {
    amount <- IO(origin.read(buffer, 0, buffer.length))
    count <- if(amount > -1)IO(destination.write(buffer, 0, amount)) >> transmit(origin, destination, buffer, acc + amount)
    else IO.pure(acc)
  } yield count

def transfer(origin: InputStream, destination: OutputStream): IO[Long] = for {
  buffer <- IO(new Array[Byte](1024 * 10))
  total <- transmit(origin, destination, buffer, 0L)
} yield total

def copy(origin: File, destination: File) : IO[Long] = {
  inputOutputStreams(origin, destination).use { case (in, out) =>
    transfer(in, out)}
}