package com.hendall.surveyrest.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.hendall.surveyrest.dto.ProvidersDTO;
import com.hendall.surveyrest.entities.ProvidersLu;

public class ProvidersAssembler {
	
	public ProvidersDTO getProvidersDTO (ProvidersLu providersLu) {
		ProvidersDTO providersDTO = new ProvidersDTO();
		providersDTO.setProviderKey(providersLu.getProviderKey());
		providersDTO.setCcn(providersLu.getCcn());
		providersDTO.setFacilityName(providersLu.getFacilityName());
		providersDTO.setStateCode(providersLu.getStatesLu().getStateCode());
		return providersDTO;
	}
	
	public List<ProvidersDTO> getProvidersDTOList (List<ProvidersLu> providersLuList) {
		List<ProvidersDTO> providersDTOList = new ArrayList<ProvidersDTO>();
		for (ProvidersLu providerLu : providersLuList) {
			providersDTOList.add(getProvidersDTO(providerLu));
		}
		return providersDTOList;
	}

}
