package com.openbanking.accounts.transactions.service;

import com.openbanking.accounts.transactions.exception.IncorrectSandBoxInputDetailException;
import com.openbanking.accounts.transactions.exception.OpenBankSandboxServiceException;
import com.openbanking.accounts.transactions.response.SandBoxTransactionsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${obp.api.rootUrl}")
    private String obpRootUrl;

    public SandBoxTransactionsResponse getTransactions(String bankId, String accountId, String viewId) throws OpenBankSandboxServiceException,IncorrectSandBoxInputDetailException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        log.info("Communicating to open bank sandbox with bank id {}, accountId {} and viewId {}", bankId, accountId, viewId);
        try {
            final ResponseEntity<SandBoxTransactionsResponse> transactionsResponseResponseEntity = restTemplate.exchange(this.obpRootUrl + "/obp/v1.2.1/banks/" + bankId + "/accounts/" + accountId + "/" + viewId + "/transactions",
                    HttpMethod.GET, new HttpEntity<String>(headers), SandBoxTransactionsResponse.class);
            return transactionsResponseResponseEntity.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("Exception occurred due to incorrect open bank sandbox input details" + ex.getMessage());
            throw new IncorrectSandBoxInputDetailException("Exception occurred due to incorrect open bank sandbox input details"+ex.getMessage());
        } catch (Exception ex) {
            log.error("Exception occurred while communication to open bank sandbox" + ex.getMessage());
            throw new OpenBankSandboxServiceException("Exception occurred while while communication to open bank sandbox" +ex.getMessage());
        }
    }
}
