package newversion.au.com.fitjournal.ui.servlet;

import com.fitbit.api.FitbitAPIException;
import com.fitbit.api.client.*;
import com.fitbit.api.client.service.FitbitAPIClientService;
import com.fitbit.api.common.model.body.DataPeriod;
import com.fitbit.api.common.model.body.WeightLog;
import com.fitbit.api.common.model.user.UserInfo;
import com.fitbit.api.model.APIResourceCredentials;
import com.fitbit.api.model.FitbitUser;
import newversion.au.com.fitjournal.service.FitBitService;
import newversion.au.com.fitjournal.service.WeightService;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/syncFitbitWeight")
public class FitbitServlet extends HttpServlet
{
    @Resource
    private FitBitService fitBitService;

    public static final String OAUTH_TOKEN = "oauth_token";
    public static final String OAUTH_VERIFIER = "oauth_verifier";

    private FitbitAPIEntityCache entityCache = new FitbitApiEntityCacheMapImpl();
    private FitbitApiCredentialsCache credentialsCache = new FitbitApiCredentialsCacheMapImpl();
    private FitbitApiSubscriptionStorage subscriptionStore = new FitbitApiSubscriptionStorageInMemoryImpl();

    private String apiBaseUrl ="api.fitbit.com";
    private String fitbitSiteBaseUrl = "http://www.fitbit.com";
    private String exampleBaseUrl = "http://localhost:8787/fitJournal";
    private String clientConsumerKey = "4d78e4a4975240988fab89c0aae6ad67";
    private String clientSecret = "22c86c8c382848d2a9e3c5d2849d8560";

    @RequestMapping
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        FitbitAPIClientService<FitbitApiClientAgent> apiClientService = new FitbitAPIClientService<FitbitApiClientAgent>(
                new FitbitApiClientAgent(apiBaseUrl, fitbitSiteBaseUrl, credentialsCache),
                clientConsumerKey,
                clientSecret,
                credentialsCache,
                entityCache,
                subscriptionStore
        );
        if (request.getParameter("completeAuthorization") != null) {
            String tempTokenReceived = request.getParameter(OAUTH_TOKEN);
            String tempTokenVerifier = request.getParameter(OAUTH_VERIFIER);
            APIResourceCredentials resourceCredentials = apiClientService.getResourceCredentialsByTempToken(tempTokenReceived);

            if (resourceCredentials == null) {
                throw new ServletException("Unrecognized temporary token when attempting to complete authorization: " + tempTokenReceived);
            }
            // Get token credentials only if necessary:
            if (!resourceCredentials.isAuthorized()) {
                // The verifier is required in the request to get token credentials:
                resourceCredentials.setTempTokenVerifier(tempTokenVerifier);
                try {
                    // Get token credentials for user:
                    apiClientService.getTokenCredentials(new LocalUserDetail(resourceCredentials.getLocalUserId()));
                } catch (FitbitAPIException e) {
                    throw new ServletException("Unable to finish authorization with Fitbit.", e);
                }
            }
            try {
                List<WeightLog> weightLog = apiClientService.getClient().getLoggedWeight(new LocalUserDetail(resourceCredentials.getLocalUserId()), new FitbitUser(resourceCredentials.getLocalUserId()), LocalDate.fromDateFields(new Date()), DataPeriod.ONE_MONTH);

                fitBitService.importFitBitWeightLog(weightLog);

            } catch (FitbitAPIException e) {
                throw new ServletException("Exception during getting user info", e);
            }
        } else {
            try {
                response.sendRedirect(apiClientService.getResourceOwnerAuthorizationURL(new LocalUserDetail("-"), exampleBaseUrl + "/syncFitbitWeight?completeAuthorization="));
            } catch (FitbitAPIException e) {
                throw new ServletException("Exception during performing authorization", e);
            }
        }
    }
}
