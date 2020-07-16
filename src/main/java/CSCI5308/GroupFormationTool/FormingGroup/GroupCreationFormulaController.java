package CSCI5308.GroupFormationTool.FormingGroup;

import CSCI5308.GroupFormationTool.AccessControl.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/groupformation")
public class GroupCreationFormulaController 
{
	private Logger log = LoggerFactory.getLogger(GroupCreationFormulaController.class);
	private static final String GROUP_FORMATION_VIEW = "groupformation";

	@GetMapping
	public String groupFormation()
	{
		return GROUP_FORMATION_VIEW;
	}
	
	@PostMapping("/create")
	public String createGroupFormationFormula(Model model,
			@RequestParam Integer groupSize,
			@RequestParam String mchoice_choose1,
			@RequestParam String mchoice_chooseMany,
			@RequestParam String numeric,
			@RequestParam Integer xValue,
			@RequestParam String studentInclude,
			@RequestParam String freetext)
	{
		log.info("Parameteres passed are: groupSize " + groupSize + " mchoiceChoose1: " + mchoice_choose1 
				+ " mchoiceChooseMany: " + mchoice_chooseMany + " numeric: " + numeric + " xValue: " + xValue
				+ " studentInclude: " + studentInclude + " freeText: " + freetext );
		
		if (null != groupSize && groupSize < 2)
		{
			model.addAttribute("error", "Group Size cannot be less than 2");
			return GROUP_FORMATION_VIEW;
		}
		return GROUP_FORMATION_VIEW;
	}
}