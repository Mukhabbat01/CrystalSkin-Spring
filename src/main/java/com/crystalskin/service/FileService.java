package com.crystalskin.service;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.crystalskin.dto.response.FileResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {
	@Value("${file.dir}")
	private String fileDir;
	
	
	@Value("$file.skinFile")
	private String skinFiles;
	

//	public String uploadFile(MultipartFile file) {
//		if (!file.isEmpty()) {
//			String uuid = UUID.randomUUID().toString();
//			String savedName = uuid + "_" + file.getOriginalFilename();
//			File dest = new File(fileDir, savedName);
//			try {
//				file.transferTo(dest);
//				return savedName;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}

	
	
	public FileResponse getFile(String name) {
		FileResponse res = new FileResponse();
		try {
			File file = new File(fileDir, name);
			res.setBytes(FileCopyUtils.copyToByteArray(file));
			res.setContentType(Files.probeContentType(file.toPath()));

		} catch (Exception e) {
		}
		return res;
	}

	
	public FileResponse getFiles(String fileDes) {
		FileResponse res = new FileResponse();
		try {
			File files = new File(fileDir, fileDes);
			res.setBytes(FileCopyUtils.copyToByteArray(files));
			res.setContentType(Files.probeContentType(files.toPath()));
		} catch (Exception e) {
		}
		return res;
	}
	
	
	
	public FileResponse getFilesBySkinType(String skinFile) {
		FileResponse res = new FileResponse();
		try {
			File files = new File(fileDir, skinFile);
			res.setBytes(FileCopyUtils.copyToByteArray(files));
			res.setContentType(Files.probeContentType(files.toPath()));
		} catch (Exception e) {
		}
		return res;
	}
	
	
//	public void crawlingAndSaveImages(String url) throws IOException{
//		Document doc = Jsoup.connect(url).get();
//		Elements images = doc.select("img");
//		
//		for (Element img : images) {
//			String imgSrc = img.absUrl("src");
////			if(isMainImage(img)) {
////				saveImage(imgSrc, mainDirectoru)
////			}
//			saveImage(imgSrc);
//		}
//	}
//		public void saveImage(String imgUrl) throws IOException{
//			try(InputStream in = new URL(imgUrl).openStream()){
//				UUID uuid = UUID.randomUUID();
//				String fileName = uuid.toString() + ".jpg";
//				Files.copy(in, Paths.get(fileDir + fileName));
//				saveToDB(uuid.toString(), fileName);
//		}
//			
//			public void SaveToDB(String uuid, String fileName) {
//				File files = new File();
//				files.setFileId(String);
//				fileRepository.save(null)
//			}
//	}
 
	
	
	
	
	
	
}