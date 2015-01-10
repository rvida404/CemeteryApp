package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistry;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistryEntryDTO;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistry;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistryEntryDTO;

import java.util.List;

/**
 * Created by Catalin Sorecau on 1/7/2015.
 */
public class RegistryRestClient extends GenericRestClient{

    public static final String BURIAL_REGISTRY_URL = "/burialRegistry";
    public static final String GRAVE_REGISTRY_URL = "/graveRegistry";
    public static final String MONUMENT_REGISTRY_URL = "/monumentRegistry";

    public static List<BurialRegistryEntryDTO> getBurialRegistry(FilterDTO filterDTO) {
        BurialRegistry burialRegistry = getJSONRestTemplate().postForObject(BASE_URL + BURIAL_REGISTRY_URL, authorizationWrapper(filterDTO), BurialRegistry.class);
        return burialRegistry.getContent();
    }

    public static Integer getBurialRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + BURIAL_REGISTRY_URL + "/count");
    }

    public static List<GraveRegistryEntryDTO> getGraveRegistry(FilterDTO filterDTO) {
        GraveRegistry graveRegistry = getJSONRestTemplate().postForObject(BASE_URL + GRAVE_REGISTRY_URL, authorizationWrapper(filterDTO), GraveRegistry.class);
        return graveRegistry.getContent();
    }

    public static Integer getGraveRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + GRAVE_REGISTRY_URL + "/count");
    }

    private static HttpEntity<Object> authorizationWrapper(Object entity) {
        return new HttpEntity<Object>(entity, new LinkedMultiValueMap<String, String>(){{
            add("Content-Type", "application/json");
            add("Authorization-Token", getLoggedInUserToken());
        }});
    }
}
