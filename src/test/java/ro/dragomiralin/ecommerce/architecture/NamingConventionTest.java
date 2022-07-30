package ro.dragomiralin.ecommerce.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = {"ro.dragomiralin.ecommerce"})
public class NamingConventionTest {

    @ArchTest
    static ArchRule services_should_be_suffixed =
            classes()
                    .that().resideInAPackage("..service")
                    .should().haveSimpleNameEndingWith("Service");

    @ArchTest
    static ArchRule controllers_should_be_suffixed =
            classes()
                    .that().resideInAPackage("..rest")
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule persistence_should_contains_adapter =
            classes()
                    .that().resideInAPackage("..persistence.adapter")
                    .should().haveSimpleNameEndingWith("Adapter");

    @ArchTest
    static ArchRule persistence_should_contains_repository =
            classes()
                    .that().resideInAPackage("..persistence.repository")
                    .should().haveSimpleNameEndingWith("Repository");

}
