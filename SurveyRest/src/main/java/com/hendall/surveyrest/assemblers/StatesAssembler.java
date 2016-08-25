package com.hendall.surveyrest.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.hendall.surveyrest.dto.StatesDTO;
import com.hendall.surveyrest.entities.StatesLu;

public class StatesAssembler {
	
	public StatesDTO getStatesDTO (StatesLu state) {
		StatesDTO stateDTO = new StatesDTO();
		
		stateDTO.setStateCode(state.getStateCode());
		stateDTO.setStateName(state.getStateName());
		
		return stateDTO;
	}
	
	public List<StatesDTO> GetStatesDTOList (List<StatesLu> statesList) {
		List<StatesDTO> statesDTOList = new ArrayList<StatesDTO>() ;
		for (StatesLu state : statesList) {
			statesDTOList.add(getStatesDTO(state));
		}
		return statesDTOList;		
	}

}
