package com.example.demo.dto;

import java.util.List;
/**
 *
 * @author 涼月
 * @date 2021/05/17
 */
public class PerformanceDTO {
    private String term;
    private List<Domains> domains;

    public void setTerm(String term) {
        this.term = term;
    }
    public String getTerm() {
        return term;
    }

    public void setDomains(List<Domains> domains) {
        this.domains = domains;
    }
    public List<Domains> getDomains() {
        return domains;
    }

    @Override
    public String toString() {
        return "PerformanceDTO{" +
                ", term='" + term + '\'' +
                ", domains=" + domains +
                '}';
    }
}
