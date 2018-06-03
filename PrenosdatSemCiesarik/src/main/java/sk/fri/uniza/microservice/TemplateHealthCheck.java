package sk.fri.uniza.microservice;

import com.codahale.metrics.health.HealthCheck;

/**
 * DEBUG trieda aplikácie.
 * @author Roman Ciesarík
 */

public class TemplateHealthCheck extends HealthCheck {

    private final String template;

     /**
    * Konštruktor inicializuje "template"   
    * @param template 
    */
    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    /**
     * Kontrla premennej "template" na obsah textu "TEST"
     * @return Result.healthy()
     * @throws Exception v prípade chyby
     */
    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
