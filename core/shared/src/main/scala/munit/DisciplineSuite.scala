package munit

import org.typelevel.discipline.Laws

trait DisciplineSuite extends ScalaCheckSuite {

  def checkAll(name: String, ruleSet: Laws#RuleSet)(implicit
    loc:             Location
  ): Unit = checkAll(new TestOptions(name, Set.empty, loc), ruleSet)

  def checkAll(options: TestOptions, ruleSet: Laws#RuleSet)(implicit
    loc:                Location
  ): Unit =
    ruleSet.all.properties.toList.foreach { case (id, prop) =>
      property(options.withName(s"${options.name}: $id")) {
        prop
      }
    }

}
