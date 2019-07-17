For testing with a different set of promotions, import the file with appropriate number of promotions and execute the script below to publish them.






import de.hybris.platform.core.Registry;
import de.hybris.platform.ruleengineservices.rule.services.RuleService;
import de.hybris.platform.ruleengineservices.maintenance.RuleMaintenanceService;
import de.hybris.platform.ruleengineservices.maintenance.RuleCompilerPublisherResult;

println "Publishing Rules"
RuleService ruleService = (RuleService) Registry.getApplicationContext().getBean("ruleService");
RuleMaintenanceService ruleMaintenanceService = (RuleMaintenanceService) Registry.getApplicationContext().getBean("ruleMaintenanceService");
RuleCompilerPublisherResult myResult = ruleMaintenanceService.compileAndPublishRules(ruleService.getAllToBePublishedRules(),"promotions-module",false);
println "----------------"
println "Batch Rule publish status for promotions-module"
println myResult.getResult()
println "----------------"
println "Done!"
return
