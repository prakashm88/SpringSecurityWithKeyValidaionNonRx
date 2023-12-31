package com.itechgenie.apps.jdk11.sbfilter.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public")
public class SBAppPublicController {

	String certsResp = "{\"keys\":[{\"kid\":\"XR4Ta04TGBlLzkjfjLMnl0U9zLgY--TEfZM9TZR_VtQ\",\"kty\":\"RSA\",\"alg\":\"RS256\",\"use\":\"sig\",\"n\":\"87uKT7qjYF-gWWMPDYSDfVS9Z9tbuaL_gozrhX7x4PaViUWXPdMijF9Xy7xj5WiU9i8LsVliZ7HltuLlVLThzgB_41DQBhhPNE7XhJBSud1DRMQAEPvFfMF5Yzc2ksknlMxTI2xCHwCKxP98KhNRwjO47g2sibdNt7j8X4S3qrkxXsXbXRusvXI9vPpw09og-jLRnVbSdfYC5wPyFDQom9vzfjpTEgfjyxpT9AW8H70APWzBBblj_1C3E_MxL1qbLC4KDlX9v1Po7KxhtlpGRclxayJ1mlYKeAn3aiUlkkSOaci8YSj4jwGp7y8uznUvM1qpaBvwuHUWcrWw2CoLAw\",\"e\":\"AQAB\",\"x5c\":[\"MIICmzCCAYMCBgGJ6wFndzANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMjMwODEyMTgyNjI1WhcNMzMwODEyMTgyODA1WjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDzu4pPuqNgX6BZYw8NhIN9VL1n21u5ov+CjOuFfvHg9pWJRZc90yKMX1fLvGPlaJT2LwuxWWJnseW24uVUtOHOAH/jUNAGGE80TteEkFK53UNExAAQ+8V8wXljNzaSySeUzFMjbEIfAIrE/3wqE1HCM7juDayJt023uPxfhLequTFexdtdG6y9cj28+nDT2iD6MtGdVtJ19gLnA/IUNCib2/N+OlMSB+PLGlP0BbwfvQA9bMEFuWP/ULcT8zEvWpssLgoOVf2/U+jsrGG2WkZFyXFrInWaVgp4CfdqJSWSRI5pyLxhKPiPAanvLy7OdS8zWqloG/C4dRZytbDYKgsDAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAOGT4ErQeobcI3FwW/vpdJCPyuwAK1tkz3DX0lquS5xfxUSpMiAP8nGAw9HjN+6gls+gQoWfJW9zEev8NxFiDPivxp5NtT9lkitx+ss0ca/rmpALR1lUs4XWM6PKD7FDOSfwDc/ndIECYNSFljNEZutqw4CyuBW1yQBE+nDgAYBcWrGaCmFoWkZUcV3aimYdrEGR0qnBqUbtieiJOctbcAA9ovDT+0iCEFuH2jTrU+YFOgCQI7ouIm7undkXybAiKOQdqJFRnXcDJAcBWg6pP/c5d63OqlC6tYpB7/jFOT4Q56Wcf6BlgDmhchDPZzcDKr8XVI6G0d7KHc6+UEsEDzs=\"],\"x5t\":\"OttpjRsx5FX8qiNSH2k6WFzna3o\",\"x5t#S256\":\"62QW1FnqFRVIrEQfcDCx2Q28Q9RjDUbzByVMDrDcS0Q\"},{\"kid\":\"9qLmmlemaZyG2VAO0SZZD3hNHbcJjOpP96_ne8uAVeU\",\"kty\":\"RSA\",\"alg\":\"RSA-OAEP\",\"use\":\"enc\",\"n\":\"uTm9IsfdA1LKEmhM6qtdovrTsq6LoMGYLLsBP33ZenEfOxiKKRTUWXhLj3MNOsfzDDzi21sk8HYsChsmsnJ4r8Ab_DHhLVXyga7MeVr4BOhMsuA5PduJL--CGuVXqwOG3V_lWoQ4gcgFf5u_kYzKdVYAa6gpYt9QYZlkCKuzAEMX_Qztoe_gY7OCmLBoFMaFTnomM2w3OikbyY_ja_9WbVgd5NVCA7ko3byBTYBCWJonqeSdGOfwxIqiN99ElM6oBLU3MGTFl0d5LIAtgmhtIq8SMJOTpYgLVoD2jjOFfFatsDiCHhTEnJKkuf50WUHPWzYiQy455D_Hlo3-iUsScw\",\"e\":\"AQAB\",\"x5c\":[\"MIICmzCCAYMCBgGJ6wFn7DANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMjMwODEyMTgyNjI1WhcNMzMwODEyMTgyODA1WjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC5Ob0ix90DUsoSaEzqq12i+tOyrougwZgsuwE/fdl6cR87GIopFNRZeEuPcw06x/MMPOLbWyTwdiwKGyaycnivwBv8MeEtVfKBrsx5WvgE6Eyy4Dk924kv74Ia5VerA4bdX+VahDiByAV/m7+RjMp1VgBrqCli31BhmWQIq7MAQxf9DO2h7+Bjs4KYsGgUxoVOeiYzbDc6KRvJj+Nr/1ZtWB3k1UIDuSjdvIFNgEJYmiep5J0Y5/DEiqI330SUzqgEtTcwZMWXR3ksgC2CaG0irxIwk5OliAtWgPaOM4V8Vq2wOIIeFMSckqS5/nRZQc9bNiJDLjnkP8eWjf6JSxJzAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAJoOCzby2XjJGPasAemy9UbyiVnnQxRekAINnl49JznwvWZNCoMyNcJ+N5uC4Ae0OsZV+wVbQqw+099nHVtF7ggN9ccBQh3GSVYcJG70ekuHmk7ztX5hxJlqbhZ+rZ63/NURwfKxMSKQo8Or9ikKWgEX1JVhcZ4mcgM1U69i98wkwUu5+iI9MdOhd/r3VxHBnL/M0JEmYkvCbsi++pmaS8Qz5mbpweOjmRca8nE9KmfFLzKyj1b7YMYvRH8LP/MgpCsFOefZBewshLjx/CBpXodZhluiDlJdJyT35HZ22CFdcPH2FraYOsDJAQH2uQoc8eGANg+pcZRhgMmm7T7Qy8c=\"],\"x5t\":\"13x_Akmu72logMcPRVgaERJ2Q3M\",\"x5t#S256\":\"TaZIGj9W679WNCePuFb1sCkSG8iW5Xkk3KsaZbfmAUA\"},{\"kid\":\"e_b0rT5lrMMVPwF1546nW07ay8ZtDjX3GwnlON6vjuI\",\"kty\":\"RSA\",\"alg\":\"RS256\",\"use\":\"sig\",\"n\":\"oIiOReN4epb4fwYau73a2KP1oWroO-b6Nmi88V3oZ7la-04o-4N0YDRN0zSIbLsQRVNruHsIElLC15kTu0KLTDME4KOAHHZRsRzNZ6MZRtTehCkFygbR8AvU1No9th99PCCv0SmMoEI56jASPkj5J_tAz3OCwV4bGxDBk9inJ37d39gq03wmF2NERgkea_KqedKg7E35S5vTeXtUqHnZvob1AtnmoIZdwuD72osFHnMbzlMGjkZN7eT_Dkq-n0BO9O1gPycy9jycXHLJzy1G0DmZJ89M3zxEMZmR5wy3M41At0P-Y6BqrSbz8r2bo5FmhvIsQWpzMk-whH8APS3IUw\",\"e\":\"AQAB\",\"x5c\":[\"MIIDeTCCAmECFAJGuFbrPg006Rfn09cFd7HmMVJeMA0GCSqGSIb3DQEBCwUAMHkxCzAJBgNVBAYTAklOMQswCQYDVQQIDAJUTjEMMAoGA1UEBwwDQ0hOMQwwCgYDVQQKDANJVEcxDTALBgNVBAsMBEFQUHMxDTALBgNVBAMMBElUR04xIzAhBgkqhkiG9w0BCQEWFGFkbWluQGl0ZWNoZ2VuaWUuY29tMB4XDTIzMDgxMjIwMzkxOFoXDTI0MDgxMTIwMzkxOFoweTELMAkGA1UEBhMCSU4xCzAJBgNVBAgMAlROMQwwCgYDVQQHDANDSE4xDDAKBgNVBAoMA0lURzENMAsGA1UECwwEQVBQczENMAsGA1UEAwwESVRHTjEjMCEGCSqGSIb3DQEJARYUYWRtaW5AaXRlY2hnZW5pZS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCgiI5F43h6lvh/Bhq7vdrYo/Whaug75vo2aLzxXehnuVr7Tij7g3RgNE3TNIhsuxBFU2u4ewgSUsLXmRO7QotMMwTgo4AcdlGxHM1noxlG1N6EKQXKBtHwC9TU2j22H308IK/RKYygQjnqMBI+SPkn+0DPc4LBXhsbEMGT2Kcnft3f2CrTfCYXY0RGCR5r8qp50qDsTflLm9N5e1Soedm+hvUC2eaghl3C4PvaiwUecxvOUwaORk3t5P8OSr6fQE707WA/JzL2PJxccsnPLUbQOZknz0zfPEQxmZHnDLczjUC3Q/5joGqtJvPyvZujkWaG8ixBanMyT7CEfwA9LchTAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAJtEYNlfadyZ61OWJl3K1mdjQlp/kSy+EHgG0y+lxFeHkBNx+626spsx2hvJVVUIFNS0bUHn0NMr36NrIp8egWsODGvqWLbpo4nvUkKNdWMAFSjnZmyvNjCuNr9tZuiGq2+KsCR3s+GPWLPMKNbkLL7tvH8Hjpy1tr9DAmhJT/IBt6tZnIgRTHu3fTjCtgucH+3bxcE6guUiuwOv6lamzFqAJQkCsNOzWWNhxxIrj7to4DvYEuFrZl+EfWcMgmSR3dLKNxPkUpXEJYHzWY6QHWUSXNvLRWXPbTGj4SgB0qVB31F/hI8ce0t7qmoGqeDqtySPRdwJI8qMMQfw6Lzpwbw=\"],\"x5t\":\"HsaNMWBkc_5zIGSIeY0WlAXJYQA\",\"x5t#S256\":\"3CGgUeY-27BqqQ_jq4S26KpbxC46P0WzCJgN4AqwKPs\"}]}";

	@GetMapping(value = "/api/user")
	public String health() throws Exception {
		log.debug("Inside health controller");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		log.debug("Logged in user info: " + auth.getName());

		String userName = auth.getName();

		return "OK".concat(userName);
	}

	@GetMapping(value = "/protocol/openid-connect/certs", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCerts() throws Exception {
		log.debug("Inside getCerts");

		return certsResp;

	}
}
