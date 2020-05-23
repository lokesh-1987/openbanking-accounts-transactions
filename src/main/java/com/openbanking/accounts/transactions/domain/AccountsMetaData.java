package com.openbanking.accounts.transactions.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountsMetaData {

     @JsonProperty("public_alias")
     private String publicAlias;
     @JsonProperty("private_alias")
     private String privateAlias;
     @JsonProperty("more_info")
     private String moreInfo;
     @JsonProperty("URL")
     private String url;
     @JsonProperty("image_URL")
     private String imageUrl;
     @JsonProperty("open_corporates_URL")
     private String openCorporateUrl;
     @JsonProperty("corporate_location")
     private String corporateLocation;
     @JsonProperty("physical_location")
     private String physicalLocation;
}
