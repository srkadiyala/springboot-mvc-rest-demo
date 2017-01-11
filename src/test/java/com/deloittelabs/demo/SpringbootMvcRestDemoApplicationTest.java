/**
 * 
 */
package com.deloittelabs.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.deloittelabs.demo.SpringbootMvcRestDemoApplication;
import com.deloittelabs.demo.entities.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootMvcRestDemoApplication.class)
@WebIntegrationTest(randomPort = true)
public class SpringbootMvcRestDemoApplicationTest {
	@Value("${local.server.port}")
	private int port;

	RestTemplate restTemplate = new RestTemplate();

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testGetAllPosts() {
		HttpEntity<String> httpEntity = getHttpEntityWithBase64Credits();

		ResponseEntity<Post[]> responseEntity = restTemplate.exchange(getRootUrl() + "/posts", HttpMethod.GET,
				httpEntity, Post[].class);

		// ResponseEntity<Post[]> responseEntity =
		// restTemplate.getForEntity(getRootUrl() + "/posts",Post[].class);
		List<Post> posts = Arrays.asList(responseEntity.getBody());
		assertNotNull(posts);
		System.out.println(posts);
	}

	
	@Test
	public void testGetPostById() {

		HttpEntity<String> httpEntity = getHttpEntityWithBase64Credits();

		ResponseEntity<Post> responseEntity = restTemplate.exchange(getRootUrl() + "/posts/1", HttpMethod.GET,
				httpEntity, Post.class);

		//Post post = restTemplate.getForObject(getRootUrl() + "/posts/1", Post.class);
		Post post = responseEntity.getBody();
		assertNotNull(post);
	}
	
	public HttpEntity<String> getHttpEntityWithBase64Credits() {

		String plainCreds = "admin:secret";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		return httpEntity;
	}

	/*
	 * @Ignore
	 * 
	 * @Test public void testCreatePost() { Post post = new Post();
	 * post.setTitle("Exploring SpringBoot REST");
	 * post.setContent("SpringBoot Learn By Example is your best friend!!");
	 * post.setCreatedOn(new Date()); HttpEntity<String> httpEntity =
	 * getHttpEntityWithBase64Credits(); ResponseEntity<Post> postResponse =
	 * restTemplate.postForEntity(getRootUrl() + "/posts", post, Post.class);
	 * 
	 * assertNotNull(postResponse); assertNotNull(postResponse.getBody());
	 * System.err.println(postResponse.getBody()); }
	 * 
	 * @Ignore
	 * 
	 * @Test public void testUpdatePost() { int id = 1; Post post =
	 * restTemplate.getForObject(getRootUrl() + "/posts/" + id, Post.class);
	 * post.setContent("This my updated post1 content"); post.setUpdatedOn(new
	 * Date());
	 * 
	 * restTemplate.put(getRootUrl() + "/posts/" + id, post);
	 * 
	 * Post updatedPost = restTemplate.getForObject(getRootUrl() + "/posts/" +
	 * id, Post.class); assertNotNull(updatedPost);
	 * System.err.println(updatedPost); }
	 * 
	 * @Ignore
	 * 
	 * @Test public void testDeletePost() { int id = 2;
	 * 
	 * Post post = restTemplate.getForObject(getRootUrl() + "/posts/" + id,
	 * Post.class); assertNotNull(post);
	 * 
	 * restTemplate.delete(getRootUrl() + "/posts/" + id);
	 * 
	 * try { post = restTemplate.getForObject(getRootUrl() + "/posts/" + id,
	 * Post.class); } catch (final HttpClientErrorException e) {
	 * assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND); } }
	 */
	

}
