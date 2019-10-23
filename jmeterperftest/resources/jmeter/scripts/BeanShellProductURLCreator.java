import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.catalog.model.classification.ClassAttributeAssignmentModel;
import java.util.List;
import java.lang.reflect.Method;
import de.hybris.platform.commerceservices.i18n.LanguageResolver;
import org.apache.commons.lang.StringUtils;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.jalo.JaloSession;
   
System.out.println("BeanShell now is executed with user rights:" + JaloSession.getCurrentSession().getUser());
JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
System.out.println("Switch to admin => BeanShell now is executed with user rights:" + JaloSession.getCurrentSession().getUser());
  
//Define the target Product Catalog  
String catalogId = "electronicsProductCatalog";

//Define the target Product Catalog Version
String catalogVersion = "Online";
  
FlexibleSearchService getFlexibleSearchService() {
return (FlexibleSearchService) Registry.getApplicationContext().getBean("flexibleSearchService");
}
  
CatalogVersionService getCatalogVersionService() {
return (CatalogVersionService) Registry.getApplicationContext().getBean("catalogVersionService");
}
  
ModelService getModelService() {
return (ModelService) Registry.getApplicationContext().getBean("modelService");
}
  
UnitService getUnitService() {
return (UnitService) Registry.getApplicationContext().getBean("unitService");
}
  

CommonI18NService getCommonI18NService() {
return (CommonI18NService) Registry.getApplicationContext().getBean("commonI18NService");
}

LanguageResolver getLanguageResolver() {
return (LanguageResolver) Registry.getApplicationContext().getBean("languageResolver");
}

List getProducts(  String catalogId,   String catalogVersionName) {    
      CatalogVersionModel catalogVersion = getCatalogVersionService().getCatalogVersion(catalogId,catalogVersionName);    
      String queryString = "SELECT {pk} " + "FROM {Product}" + "WHERE {catalogVersion} = '" + catalogVersion.getPk()+ "' and {approvalStatus}=({{SELECT {pk} FROM {ArticleApprovalStatus} WHERE {code}='approved'}})";
      FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
      List result = getFlexibleSearchService().search(query).getResult();
      System.out.println(result.size() + " products found for given criteria");
      return result;
    }
    
boolean isNumeric(String str)  
  {  
    try  
    {  
      int i = Integer.parseInt(str);  
    }  
    catch(NumberFormatException nfe)  
    {  
      return false;  
    }  
    return true;  
}
  
  LanguageModel languageModel = getLanguageResolver().getLanguage("en");

  getCommonI18NService().setCurrentLanguage(languageModel);

    DefaultProductModelUrlResolver productModelUrlResolver = (DefaultProductModelUrlResolver) Registry.getApplicationContext().getBean("productModelUrlResolver");      
    
    List products = getProducts(catalogId, catalogVersion);
    
        for(ProductModel product : products) {
            //Add here any validation of product code format needed. Examples:
        	//if(!isNumeric(product.getCode()) && (StringUtils.countMatches(product.getCode(), "-")>=2))          
            out.println(productModelUrlResolver.resolve(product));
        }

