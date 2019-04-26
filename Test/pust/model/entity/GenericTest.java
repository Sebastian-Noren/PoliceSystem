package pust.model.entity;
import org.junit.*;
import pust.model.entity.entity_builder.SuspectBuilder;
import pust.model.entity.entity_builder.VisitorBuilder;
import pust.model.enumerations.Color;
import static org.junit.Assert.*;

public class GenericTest {

    @Test
    public void createVisitorWithFirstName() {
        // Arrange
        final String expected = "Christoffer";

        // Act
        final Visitor actual = (Visitor) new VisitorBuilder().withFirstName("Christoffer").build();

        // Assert
        Assert.assertEquals(expected, actual.getFirstName());
    }

    @Test
    public void createSuspectWithYellowHair(){
        final Color expected = Color.YELLOW;

        final Suspect actual = (Suspect) new SuspectBuilder().withHairColor(Color.YELLOW).build();

        Assert.assertEquals(expected, actual.getHairColor());
    }




}
