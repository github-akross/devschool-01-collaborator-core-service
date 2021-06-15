package br.com.devschool.collaboratorcore.infrastructure.repository.api;


import br.com.devschool.collaboratorcore.domain.dto.BlackList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "blacklist-api", url = "localhost:8080" )
@FeignClient(value = "blacklist-api", url = "${application.blacklist-api.url}")
public interface BlackListApi {
    @GetMapping("{cpf}")
    BlackList getBlacklistByCpf(@PathVariable String cpf);
}
