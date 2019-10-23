import de.hybris.platform.commerceservices.i18n.LanguageResolver;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import java.util.List;
 
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.jalo.JaloSession;
   
System.out.println("BeanShell now is executed with user rights:" + JaloSession.getCurrentSession().getUser());
JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
System.out.println("Switch to admin => BeanShell now is executed with user rights:" + JaloSession.getCurrentSession().getUser());

UserService getUserService() {
return (UserService) Registry.getApplicationContext().getBean("userService");
}
  
FlexibleSearchService getFlexibleSearchService() {
return (FlexibleSearchService) Registry.getApplicationContext().getBean("flexibleSearchService");
}

ModelService getModelService() {
return (ModelService) Registry.getApplicationContext().getBean("modelService");
}
  

CommonI18NService getCommonI18NService() {
return (CommonI18NService) Registry.getApplicationContext().getBean("commonI18NService");
}

LanguageResolver getLanguageResolver() {
return (LanguageResolver) Registry.getApplicationContext().getBean("languageResolver");
}

List getAllCustomers() {    
	  String queryString = "select * from {Customer} where {name} != 'Anonymous'";
      FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
      List result = getFlexibleSearchService().search(query).getResult();
      System.out.println(result.size() + " customers found for given criteria");
      return result;
    }
  
  LanguageModel languageModel = getLanguageResolver().getLanguage("en");

  getCommonI18NService().setCurrentLanguage(languageModel);

    List customers = getAllCustomers();
    
    for(CustomerModel customer : customers) {
    	System.out.println(customer.uid);
    }
