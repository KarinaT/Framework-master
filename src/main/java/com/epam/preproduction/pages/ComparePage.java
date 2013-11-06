package com.epam.preproduction.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epam.preproduction.entities.Microwave;

public class ComparePage extends Page {

	public ComparePage(WebDriver driver) {
		super(driver);
	}

	public List<Microwave> grabAllParams() {
		List<Microwave> microwaves = new ArrayList<Microwave>();
		Microwave microwave1 = new Microwave();
		Microwave microwave2 = new Microwave();
		microwaves.add(microwave1);
		microwaves.add(microwave2);

		List<WebElement> comparePageCharacteristics = getDriver().findElements(By.xpath("//table[@class='compare']/tbody/tr[@class='']"));
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = element.findElement(By.xpath(".//td[1]")).getText();
			String firstItemValues = element.findElement(By.xpath(".//td[2]")).getText();
			String secondItemValues = element.findElement(By.xpath(".//td[3]")).getText();
			microwave1.getCharacteristics().put(characteristicName, firstItemValues);
			microwave2.getCharacteristics().put(characteristicName, secondItemValues);
		}
		return microwaves;
	}
	
	public Set<String> grabAllParamNames() {
		Set<String> characteristicsNames= new HashSet<String>();

		List<WebElement> comparePageCharacteristics = getDriver().findElements(By.xpath("//table[@class='compare']/tbody/tr[@class='']"));
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = element.findElement(By.xpath(".//td[1]")).getText();
			characteristicsNames.add(characteristicName);
		}
		comparePageCharacteristics = getDriver().findElements(By.xpath("//table[@class='compare']/tbody/tr[@class='different']"));
		for (WebElement element : comparePageCharacteristics) {
			String characteristicName = element.findElement(By.xpath(".//td[1]")).getText();
			characteristicsNames.add(characteristicName);
		}
		return characteristicsNames;
	}
}

