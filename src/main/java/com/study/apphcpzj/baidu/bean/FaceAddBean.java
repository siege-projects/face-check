package com.study.apphcpzj.baidu.bean;

/**
 * @author ztt : 2019-03-26
 */
public class FaceAddBean extends BaseBean {


	/**
	 * face_token : 2fa64a88a9d5118916f9a303782a97d3
	 * location : {"left":117,"top":131,"width":172,"height":170,"rotation":4}
	 */

	private String face_token;
	private LocationBean location;

	public String getFace_token() {
		return face_token;
	}

	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}

	public LocationBean getLocation() {
		return location;
	}

	public void setLocation(LocationBean location) {
		this.location = location;
	}

	public static class LocationBean {
		/**
		 * left : 117
		 * top : 131
		 * width : 172
		 * height : 170
		 * rotation : 4
		 */

		private int left;
		private int top;
		private int width;
		private int height;
		private int rotation;

		public int getLeft() {
			return left;
		}

		public void setLeft(int left) {
			this.left = left;
		}

		public int getTop() {
			return top;
		}

		public void setTop(int top) {
			this.top = top;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getRotation() {
			return rotation;
		}

		public void setRotation(int rotation) {
			this.rotation = rotation;
		}
	}
}
