package baobao.account_service.controller;

import baobao.account_service.dto.AccountDTO;
import baobao.account_service.entity.Account;
import baobao.account_service.feignclient.ArtworkFeignClient;
import baobao.account_service.service.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    private final IAccountService acService;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final ArtworkFeignClient artworkFeignClient;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getListAccounts() {
        final List<Account> accountEntities = acService.getListAccounts();
        final List<AccountDTO> lsAccountDTO = modelMapper.map(
                accountEntities,
                new TypeToken<List<AccountDTO>>() {
                }.getType());
        return ResponseEntity.ok(lsAccountDTO);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable("accountId") int accountId) {
        final Account accountEntity = acService.findAccountById(accountId);
        final AccountDTO accountDTO = modelMapper.map(
                accountEntity,
                new TypeToken<AccountDTO>() {
                }.getType());
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/hello")
    public String gretting(){
//        String str = restTemplate.getForObject("http://localhost:8080/api/v1/artworks", String.class);
//        Docker must have
        String str = artworkFeignClient.gretting();
        log.info("Artwork demo restTemplate : {}", str);
        return str;
    }
}
