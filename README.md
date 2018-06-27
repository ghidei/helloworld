# Description (sample taken from [akka-samples](https://github.com/akka/akka-samples/tree/2.5/akka-sample-main-scala))

## The Obligatory Hello World

Since every programming paradigm needs to solve the tough problem of printing a well-known greeting to the console we’ll introduce you to the actor-based version.

Open [HelloWorld.scala](src/main/scala/sample/hello/HelloWorld.scala).

The `HelloWorld` actor is the application’s “main” class; when it terminates the application will shut down—more on that later. The main business logic happens in the `preStart` method, where a `Greeter` actor is created and instructed to issue that greeting we crave for. When the greeter is done it will tell us so by sending back a message, and when that message has been received it will be passed into the behavior described by the `receive`method where we can conclude the demonstration by stopping the `HelloWorld`actor.

## The Greeter

You will be very curious to see how the `Greeter` actor performs the actual task. Open [Greeter.scala](src/main/scala/sample/hello/Greeter.scala).

This is extremely simple now: after its creation this actor will not do anything until someone sends it a message, and if that happens to be an invitation to greet the world then the `Greeter` complies and informs the requester that the deed has been done.

## Main class

Start the application main class: `sbt "runMain sample.hello.Main"`. In the log output you can see the "Hello World!" greeting.


## To run [ldfi-akka](https://github.com/KTH/ldfi-akka)

1. Checkout fresh branch

	`git checkout -b <branch_name>`

2. Clone ldfi-akka to branch root

	`git clone https://github.com/KTH/ldfi-akka.git`

3. Add the following dependency to build.sbt

	```
	lazy val ldfiakka = (project in file ("ldfi-akka"))
	.settings(
		name := "ldfi-akka",
		mainClass in Compile := Some("ldfi.akka.Main"))
	.dependsOn(global)
	```
4. Compile project

	`sbt compile`

5. Copy code in to ldfi-akka

	`sbt "ldfiakka/runMain ldfi.akka.Main --copy src/main/scala"`

6. Compile ldfi-akka

	`(cd ldfi-akka; sbt compile)`

7. Rewrite code

	`sbt "ldfiakka/runMain ldfi.akka.Main --rewrite"`

6. Compile ldfi-akka

	`(cd ldfi-akka; sbt compile)`

9. Run ldfi-akka

	`sbt "ldfiakka/runMain ldfi.akka.Main -m src/main/scala/sample/hello/Main.scala -v src/main/scala/sample/hello/Main.scala verifyCorrectness"`
