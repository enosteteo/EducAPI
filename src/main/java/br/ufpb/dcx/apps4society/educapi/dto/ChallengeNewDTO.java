package br.ufpb.dcx.apps4society.educapi.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.ufpb.dcx.apps4society.educapi.domain.Context;
import br.ufpb.dcx.apps4society.educapi.domain.User;
import org.hibernate.validator.constraints.Length;

public class ChallengeNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message="Required")
	@Length(min=5, max=50, message="The size must be between 5 and 50 characters")
	private String word;
	
	private String soundUrl;
	private String videoUrl;
	private String imageUrl;

	private User creator;

	private List<Context> contexts;
	
	public ChallengeNewDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getSoundUrl() {
		return soundUrl;
	}

	public void setSoundUrl(String soundUrl) {
		this.soundUrl = soundUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Context> getContexts() {
		return contexts;
	}

	public void setContexts(List<Context> contexts) {
		this.contexts = contexts;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
