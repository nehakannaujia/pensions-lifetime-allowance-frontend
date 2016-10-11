/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package constructors

import uk.gov.hmrc.http.cache.client.CacheMap
import uk.gov.hmrc.play.test.{WithFakeApplication, UnitSpec}
import play.api.libs.json.{JsValue, Json}
import models._
import enums.ApplicationType

class SummaryConstructorSpec extends UnitSpec with WithFakeApplication {
  object TestSummaryConstructor extends SummaryConstructor
  val tstId = "testUserID"
  "Summary Constructor" should {

    val positivePensionsTakenTuple = "pensionsTaken" -> Json.toJson(PensionsTakenModel(Some("yes")))
    val negativePensionsTakenTuple = "pensionsTaken" -> Json.toJson(PensionsTakenModel(Some("no")))
    val negativePensionsTakenSummaryRow = SummaryRowModel("pensionsTaken", Some(controllers.routes.IP2016Controller.pensionsTaken()), false, "No")
    val positivePensionsTakenSummaryRow = SummaryRowModel("pensionsTaken", Some(controllers.routes.IP2016Controller.pensionsTaken()), false, "Yes")

    val positivePensionsTakenBeforeTuple = "pensionsTakenBefore" -> Json.toJson(PensionsTakenBeforeModel("yes", Some(BigDecimal(1001000))))
    val negativePensionsTakenBeforeTuple = "pensionsTakenBefore" -> Json.toJson(PensionsTakenBeforeModel("no", None))
    val positivePensionsTakenBeforeSummaryRow = SummaryRowModel("pensionsTakenBefore", Some(controllers.routes.IP2016Controller.pensionsTakenBefore()), false, "Yes")
    val positivePensionsTakenBeforeAmtSummaryRow = SummaryRowModel("pensionsTakenBeforeAmt", Some(controllers.routes.IP2016Controller.pensionsTakenBefore()), false, "£1,001,000")
    val negativePensionsTakenBeforeSummaryRow = SummaryRowModel("pensionsTakenBefore", Some(controllers.routes.IP2016Controller.pensionsTakenBefore()), false, "No")

    val positivePensionsTakenBetweenTuple = "pensionsTakenBetween" -> Json.toJson(PensionsTakenBetweenModel("yes", Some(BigDecimal(1100))))
    val negativePensionsTakenBetweenTuple = "pensionsTakenBetween" -> Json.toJson(PensionsTakenBetweenModel("no", None))
    val positivePensionsTakenBetweenSummaryRow = SummaryRowModel("pensionsTakenBetween", Some(controllers.routes.IP2016Controller.pensionsTakenBetween()), false, "Yes")
    val positivePensionsTakenBetweenAmtSummaryRow = SummaryRowModel("pensionsTakenBetweenAmt", Some(controllers.routes.IP2016Controller.pensionsTakenBetween()), false, "£1,100")
    val negativePensionsTakenBetweenSummaryRow = SummaryRowModel("pensionsTakenBetween", Some(controllers.routes.IP2016Controller.pensionsTakenBetween()), false, "No")

    val positiveOverseasPensionsTuple = "overseasPensions" -> Json.toJson(OverseasPensionsModel("yes", Some(BigDecimal(1010))))
    val negativeOverseasPensionsTuple = "overseasPensions" -> Json.toJson(OverseasPensionsModel("no", None))
    val negativeOverseasPensionsSummaryRow = SummaryRowModel("overseasPensions", Some(controllers.routes.IP2016Controller.overseasPensions()), false, "No")
    val positiveOverseasPensionsSummaryRow = SummaryRowModel("overseasPensions", Some(controllers.routes.IP2016Controller.overseasPensions()), false, "Yes")
    val positiveOverseasPensionsAmtSummaryRow = SummaryRowModel("overseasPensionsAmt", Some(controllers.routes.IP2016Controller.overseasPensions()), false, "£1,010")

    val validCurrentPensionsTuple = "currentPensions" -> Json.toJson(CurrentPensionsModel(Some(BigDecimal(1001))))
    val currentPensionsSummaryRow = SummaryRowModel("currentPensionsAmt", Some(controllers.routes.IP2016Controller.currentPensions()), false, "£1,001")

    val positivePensionDebitsTuple =  "pensionDebits" -> Json.toJson(PensionDebitsAmtModel(Some("yes")))
    val negativePensionDebitsTuple =  "pensionDebits" -> Json.toJson(PensionDebitsAmtModel(Some("no")))
    val negativePensionDebitsSummaryRow = SummaryRowModel("pensionDebits", Some(controllers.routes.IP2016Controller.pensionDebits()), false, "No")
    val positivePensionDebitsSummaryRow = SummaryRowModel("pensionDebits", Some(controllers.routes.IP2016Controller.pensionDebits()), false, "Yes")

    def totalPensionsAmountSummaryRow(totalAmount: String) = SummaryRowModel("totalPensionsAmt", None, true, totalAmount)

    def numberOfPSOsTuple(numberOfPSOs: Int): (String, JsValue) = "numberOfPSOs" -> Json.toJson(NumberOfPSOsModel(Some(numberOfPSOs.toString)))
    def numberOfPSOsSummaryRow(numberOfPSOs: Int) = SummaryRowModel("numberOfPSOsAmt", Some(controllers.routes.IP2016Controller.numberOfPSOs()), false, numberOfPSOs.toString)

    val psoDetails1Tuple = "psoDetails1" -> Json.toJson(PSODetailsModel(1, Some(1), Some(2), Some(2016), BigDecimal(10000)))
    val psoDetails2Tuple = "psoDetails2" -> Json.toJson(PSODetailsModel(2, Some(2), Some(3), Some(2016), BigDecimal(11000)))
    val psoDetails3Tuple = "psoDetails3" -> Json.toJson(PSODetailsModel(3, Some(3), Some(4), Some(2016), BigDecimal(10100)))
    val psoDetails4Tuple = "psoDetails4" -> Json.toJson(PSODetailsModel(4, Some(4), Some(5), Some(2016), BigDecimal(10010)))
    val psoDetails5Tuple = "psoDetails5" -> Json.toJson(PSODetailsModel(5, Some(5), Some(6), Some(2016), BigDecimal(10001)))

    val psoDetails1SummaryRow = SummaryRowModel("psoDetails1", Some(controllers.routes.IP2016Controller.psoDetails("1")), false, "£10,000", "1 February 2016")
    val psoDetails2SummaryRow = SummaryRowModel("psoDetails2", Some(controllers.routes.IP2016Controller.psoDetails("2")), false, "£11,000", "2 March 2016")
    val psoDetails3SummaryRow = SummaryRowModel("psoDetails3", Some(controllers.routes.IP2016Controller.psoDetails("3")), false, "£10,100", "3 April 2016")
    val psoDetails4SummaryRow = SummaryRowModel("psoDetails4", Some(controllers.routes.IP2016Controller.psoDetails("4")), false, "£10,010", "4 May 2016")
    val psoDetails5SummaryRow = SummaryRowModel("psoDetails5", Some(controllers.routes.IP2016Controller.psoDetails("5")), false, "£10,001", "5 June 2016")

    "handle invalid summary data" when {
        implicit val protectionType = ApplicationType.IP2016

      "there is no data" in {
        val tstMap = CacheMap(tstId, Map.empty)
        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "there is no pensions taken model" in {
        val tstMap = CacheMap(tstId, Map(positivePensionsTakenBeforeTuple,
                                        positivePensionsTakenBetweenTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        negativePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "there is no overseas pensions model" in {
        val tstMap = CacheMap(tstId, Map(negativePensionsTakenTuple,
                                        validCurrentPensionsTuple,
                                        negativePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "there is no current pensions model" in {
        val tstMap = CacheMap(tstId, Map(negativePensionsTakenTuple,
                                        positiveOverseasPensionsTuple,
                                        negativePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "pensions taken 'yes' and no pensions taken before model" in {
        val tstMap = CacheMap(tstId, Map(positivePensionsTakenTuple,
                                        positivePensionsTakenBetweenTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        negativePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "pensions taken 'yes' and no pensions taken between model" in {
        val tstMap = CacheMap(tstId, Map(positivePensionsTakenTuple,
                                        positivePensionsTakenBeforeTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        negativePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "pension debits 'yes' and no number of PSOs model" in {
        val tstMap = CacheMap(tstId, Map(negativePensionsTakenTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        positivePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }

      "pension debits 'yes', 3 PSOs and not enough PSO details models" in {
        val tstMap = CacheMap(tstId, Map(negativePensionsTakenTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        positivePensionDebitsTuple,
                                        numberOfPSOsTuple(3),
                                        psoDetails1Tuple,
                                        psoDetails2Tuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe None
      }
    }

    "handle valid summary data" when {
        implicit val protectionType = ApplicationType.IP2016

      "all answers are neagtive" in {
        val testSummaryModel = SummaryModel(protectionType, true, List(
                                                                    SummarySectionModel(List(
                                                                      negativePensionsTakenSummaryRow)),
                                                                    SummarySectionModel(List(
                                                                      negativeOverseasPensionsSummaryRow)),
                                                                    SummarySectionModel(List(
                                                                      currentPensionsSummaryRow)),
                                                                    SummarySectionModel(List(
                                                                      totalPensionsAmountSummaryRow("£1,001")))
                                                                    ),
                                                                    List(
                                                                      SummarySectionModel(
                                                                        List(negativePensionDebitsSummaryRow))
                                                                    )
        )

        val tstMap = CacheMap(tstId, Map(negativePensionsTakenTuple,
                                        negativeOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        negativePensionDebitsTuple))

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe Some(testSummaryModel)
      }

      "all answers are positive" in {
        val testSummaryModel = SummaryModel(protectionType, false,
                                        List(
                                          SummarySectionModel(List(
                                            positivePensionsTakenSummaryRow)),
                                          SummarySectionModel(List(
                                            positivePensionsTakenBeforeSummaryRow, positivePensionsTakenBeforeAmtSummaryRow)),
                                          SummarySectionModel(List(
                                            positivePensionsTakenBetweenSummaryRow, positivePensionsTakenBetweenAmtSummaryRow)),
                                          SummarySectionModel(List(
                                            positiveOverseasPensionsSummaryRow, positiveOverseasPensionsAmtSummaryRow)),
                                          SummarySectionModel(List(
                                            currentPensionsSummaryRow)),
                                          SummarySectionModel(List(
                                            totalPensionsAmountSummaryRow("£1,004,111")))
                                            ),
                                        List(
                                          SummarySectionModel(List(
                                            positivePensionDebitsSummaryRow)),
                                          SummarySectionModel(List(
                                            numberOfPSOsSummaryRow(5))),
                                          SummarySectionModel(List(
                                            psoDetails1SummaryRow)),
                                          SummarySectionModel(List(
                                            psoDetails2SummaryRow)),
                                          SummarySectionModel(List(
                                            psoDetails3SummaryRow)),
                                          SummarySectionModel(List(
                                            psoDetails4SummaryRow)),
                                          SummarySectionModel(List(
                                            psoDetails5SummaryRow))
                                            )
                                        )

        val tstMap = CacheMap(tstId, Map(positivePensionsTakenTuple,
                                        positivePensionsTakenBeforeTuple,
                                        positivePensionsTakenBetweenTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        positivePensionDebitsTuple,
                                        numberOfPSOsTuple(5),
                                        psoDetails1Tuple,
                                        psoDetails2Tuple,
                                        psoDetails3Tuple,
                                        psoDetails4Tuple,
                                        psoDetails5Tuple
                                        )
                            )

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe Some(testSummaryModel)
      }

      "pensions taken 'yes', pensions taken before 'no', pensions taken between 'no'" in {
        val testSummaryModel = SummaryModel(protectionType, true,
                                        List(
                                          SummarySectionModel(List(
                                            positivePensionsTakenSummaryRow)),
                                          SummarySectionModel(List(
                                            negativePensionsTakenBeforeSummaryRow)),
                                          SummarySectionModel(List(
                                            negativePensionsTakenBetweenSummaryRow)),
                                          SummarySectionModel(List(
                                            positiveOverseasPensionsSummaryRow, positiveOverseasPensionsAmtSummaryRow)),
                                          SummarySectionModel(List(
                                            currentPensionsSummaryRow)),
                                          SummarySectionModel(List(
                                            totalPensionsAmountSummaryRow("£2,011")))
                                            ),
                                        List(
                                          SummarySectionModel(List(
                                            negativePensionDebitsSummaryRow)))
                                        )

        val tstMap = CacheMap(tstId, Map(positivePensionsTakenTuple,
                                        negativePensionsTakenBeforeTuple,
                                        negativePensionsTakenBetweenTuple,
                                        positiveOverseasPensionsTuple,
                                        validCurrentPensionsTuple,
                                        negativePensionDebitsTuple
                                        )
                            )

        TestSummaryConstructor.createSummaryData(tstMap) shouldBe Some(testSummaryModel)
      }
    }
  }
}
