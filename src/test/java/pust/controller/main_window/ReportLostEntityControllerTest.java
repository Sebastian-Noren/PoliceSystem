package pust.controller.main_window;

import org.junit.Test;
import pust.model.administrative_functions.ItemReportReceipt;
import pust.model.administrative_functions.report_system.report.MissingItemReport;
import pust.model.administrative_functions.report_system.report_builder.MissingItemReportBuilder;
import pust.model.entity.Address;
import pust.model.entity.Notifier;
import pust.model.entity.PersonalNumber;
import pust.model.entity.Police;
import pust.model.entity.entity_builder.NotifierBuilder;
import pust.model.entity.entity_builder.PoliceBuilder;
import pust.model.enumerations.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ReportLostEntityControllerTest {

    @Test
    public void submitBtnPressed() {
        ItemReportReceipt itemReportReceipt = new ItemReportReceipt(generateMissingItemReport());
    }


    private MissingItemReport generateMissingItemReport() {

        return (MissingItemReport) new MissingItemReportBuilder()
                .withSpecificCharacteristics("A very handsome person")
                .withProductionNumber("0990-45-58899-M")
                .withModel("Park")
                .withMaterial("Plastic and metal")
                .withMarking("Marking")
                .withManufacturer("Stiga")
                .withColor(Color.eyeColor.BLUE)
                .withAreaOfUse("Outside")
                .hasRef("A new ref")
                .withDescriptionOfEvent("Some descripion of event")
                .isCurrentDate(LocalDate.now())
                .wasTimeAndDateOfEvent(LocalDate.now())
                .wasPlaceOfEvent(new Address("Spångvägen 12", 28020, "Bjärnum", "Sverige"))
                .theNotifier(
                        (Notifier) new NotifierBuilder()
                                .withFirstName("Julius")
                                .withSurname("Soutine")
                                .withPersonalNumber(new PersonalNumber(1981, 12, 02, 555, 5))
                                .withAddress(new Address("Spångvägen 12", 28020, "Bjärnum", "Sverige"))
                                .withPhoneNumber("0733897895")
                                .build()
                )
                .hasAdministrativeOfficer(
                        (Police) new PoliceBuilder()
                                .withFirstName("Christoffer")
                                .withSurname("Quick")
                                .build()
                )
                .build();
    }
}