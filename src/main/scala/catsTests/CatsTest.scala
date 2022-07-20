import cats.effect.{Concurrent, IO, Resource}
import cats.syntax.all._
import cats.effect.concurrent.Semaphore

import java.io.{File, FileInputStream, FileOutputStream, InputStream, OutputStream}

def inputStream(file: File, guard: Semaphore[IO]): Resource[IO, FileInputStream] =
  Resource.make {
    IO(new FileInputStream(file))
  } { inStream =>
    guard.withPermit {
      IO(inStream.close()).handleErrorWith(_ => IO.unit) // TODO add logging
    }
  }

def outputStream(file: File, guard: Semaphore[IO]): Resource[IO, FileOutputStream] =
  Resource.make {
    IO( new FileOutputStream(file))
  } { outputStream =>
    guard.withPermit {
      IO(outputStream.close()).handleErrorWith(_ => IO.unit) // TODO add logging
    }
  }

def inputOutputStreams(input: File, output: File, guard: Semaphore[IO]): Resource[IO, (InputStream, OutputStream)] =
  for {
    inStream <- inputStream(input, guard)
    outStream <- outputStream(output, guard)
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

def copy(origin: File, destination: File)(implicit concurrent: Concurrent[IO]): IO[Long] = {
  for {
    guard <- Semaphore[IO](1)
    count <- inputOutputStreams(origin, destination, guard).use { case (in, out) =>
      guard.withPermit(transfer(in, out))
    }
  } yield count
}