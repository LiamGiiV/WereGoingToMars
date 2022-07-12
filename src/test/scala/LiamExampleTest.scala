//import org.scalatest.funsuite.AnyFunSuite
//import org.scalatest.Tag
//import org.scalatest.OneInstancePerTest
//import org.scalatest.BeforeAndAfter
//
//import scala.collection.mutable
//import scala.collection.mutable.ListBuffer
//// import reflect.Selectable.reflectiveSelectable
//
//object TaggedTest
//    extends Tag(
//      "ExampleTag"
//    ) // All tests with this tag will be grouped together
//object FullyQualifiedNameTest
//    extends Tag("com.scenario_world.tags.FullyQualifiedNameTest")
//
//class TestProof
//    extends AnyFunSuite
//    with OneInstancePerTest
//    with BeforeAndAfter {
//
//  before {
//    info(
//      "This is an \"Informer\" that will run BEFORE each test that's in this class."
//    )
//  }
//
//  after {
//    info(
//      "This is an \"Informer\" that will run AFTER each test that's in this class."
//    )
//  }
//
//  def testFixture: Object {
//    val string: String
//    val stringBuffer: ListBuffer[String]
//  } = new {
//    val string = new String("Test String")
//    val stringBuffer = new ListBuffer[String]
//  }
//
//  trait testTrait {
//    val stringList = new ListBuffer[String]
//  }
//
//  test(
//    "Example Test as Reference Material and to Prove the Test Suite is in Working Order",
//    TaggedTest
//  ) {
//
//    // testing against anonymous instances of a trait
//    new testTrait {
//      stringList += "test"
//      assert(stringList.nonEmpty)
//    }
//
//    // testing fixture vals
//    val testVal = testFixture
//    assert(testVal.string == "Test String")
//  }
//}
