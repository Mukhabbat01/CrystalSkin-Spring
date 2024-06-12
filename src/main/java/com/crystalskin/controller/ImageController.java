package com.crystalskin.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crystalskin.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImageController {

	private final FileService fileService;
//	
//	@GetMapping("/crawl-images")
//    public String crawlImages(@RequestParam String url) {
//        try {
//            fileService.crawlingAndSaveImages(url);
//            return "Images crawled and saved successfully!";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Failed to crawl images: " + e.getMessage();
//        }
//	}
}
