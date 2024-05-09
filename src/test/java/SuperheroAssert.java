import io.qameta.allure.Step;
import models.Superhero;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperheroAssert {

    @Step("Check that superhero has been created correctly")
    public static void checkCreatedHero (Superhero request, Superhero response) {
        assertThat(request.getCity()).as("check city").isEqualTo(response.getCity());
        assertThat(request.getPhone()).as("check phone").isEqualTo(response.getPhone());
        assertThat(request.getFullName()).as("check name").isEqualTo(response.getFullName());
        assertThat(request.getMainSkill()).as("check skill").isEqualTo(response.getMainSkill());
        assertThat(request.getGender()).as("check gender").isEqualTo(response.getGender());
    }

}
