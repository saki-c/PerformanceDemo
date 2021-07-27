package com.example.demo.dto;

import java.util.List;
/**
 *
 * @author 咲蛍
 * @date 2021/05/17
 */
public class PerformanceDTO {
    private String term;
    private List<DomainsDTO> domainsDTO;

    public void setTerm(String term) {
        this.term = term;
    }
    public String getTerm() {
        return term;
    }

    public List<DomainsDTO> getDomainsDTO() {
        return domainsDTO;
    }

    public void setDomainsDTO(List<DomainsDTO> domainsDTO) {
        this.domainsDTO = domainsDTO;
    }

    @Override
    public String toString() {
        return "PerformanceDTO{" +
                "term='" + term + '\'' +
                ", domainsDTO=" + domainsDTO +
                '}';
    }
}
