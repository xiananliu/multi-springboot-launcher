package io.github.xiananliu.msl;

import lombok.Data;

@Data
public class Config {

    private Item[] services;

    @Data
    public static class Item {

        private String classpath;
        private String[] args;

    }


}
