package com.example.demo.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
/**
 *
 * @author 咲蛍
 * @date 2021/05/17
 */
public class PerformanceDTO {
    @NotEmpty(message = "考核周期不能为空")
    private String term;

    private List<DomainsDTO> domains;

    public void setTerm(String term) {
        this.term = term;
    }
    public String getTerm() {
        return term;
    }

    public List<DomainsDTO> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainsDTO> domains) {
        this.domains = domains;
    }

    @Override
    public String toString() {
        return "PerformanceDTO{" +
                "term='" + term + '\'' +
                ", domains=" + domains +
                '}';
    }
}
