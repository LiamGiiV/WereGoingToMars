#We're Going To Mars! - v0.0.1.0

For the fine folks at Kinaxis.

Mission 1:

Usage:

- `sbt compile` at `./` to compile.
- `sbt run` at `./` to run.
- `sbt test` at `./` to test.

Some requirements:

- Scala 2.13.8
- sbt latest

Some suggestions for best results:
- Written with Intellij
- File handling only tested on a Windows and Linux via WSL2 due to availability of devices
- The `package` structure with `liamgiiv.weregoingtomars` in `build.sbt` seems to break or unbreak depending on the version of sbt, if you can't use the latest sbt for some reason make sure that line is commented out in `build.sbt`

Mission 2:

Usage:

- open ./data/Mission-2-Liam-Gallagher.png

*Built and tested on a Windows machine which shouldn't matter, but Java's promise of being OS angnostic has yet to be realised and some of the OS file handling stuff can be pretty arcane.*
