package com.kosmo.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.mintchoco.tag.TagDAO;
import com.kosmo.mintchoco.tag.TagVO;

@Controller
public class TagController {

	@RequestMapping(value="/tag/list.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getTagList(@RequestParam(value="type", defaultValue="1") int type, TagDAO tagDAO) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		List<TagVO> outputList = new ArrayList<TagVO>();
		
		outputList = tagDAO.selectTagList(type);
		
		String returnString = mapper.writeValueAsString(outputList);
	    return new ResponseEntity<String>(returnString, responseHeaders, HttpStatus.CREATED);
	}
}
