package utils;

import apiutil.ApiUtils;
import aquality.selenium.core.utilities.JsonSettingsFile;
import consts.ConfigEnum;
import consts.Endpoints;
import consts.Params;
import models.TestInfoModel;
import java.util.HashMap;
import java.util.List;

public class ApiUtil {
    private ApiUtil() {}

    public static String getToken(Endpoints endpoints, String variant) {
       HashMap<String ,String> query = new HashMap<>();
       query.put(Params.VARIANT.label, variant);
       return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
               .getValue(ConfigEnum.BASE_URL_API.label).toString() + endpoints.label,query)
               .asString();
   }

   public static List<TestInfoModel> getTestsInfo(Endpoints endpoints, String projectId) {
       HashMap<String ,String> query = new HashMap<>();
       query.put(Params.PROJECT_ID.label, projectId);
       return ApiUtils.getResponse(new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label)
               .getValue(ConfigEnum.BASE_URL_API.label).toString() + endpoints.label,query)
               .jsonPath()
               .getList("",TestInfoModel.class);
   }

}
