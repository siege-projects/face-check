package com.study.apphcpzj.baidu.bean;

import java.util.List;

/**
 * @author ztt : 2019-03-26
 */
public class RlcxBean extends BaseBean{


	/**
	 * timestamp : 1553582935
	 * cached : 0
	 */

	private int timestamp;
	private int cached;
	private ResultBean result;

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getCached() {
		return cached;
	}

	public void setCached(int cached) {
		this.cached = cached;
	}

	public ResultBean getResult() {
		return result;
	}

	public void setResult(ResultBean result) {
		this.result = result;
	}

	public static class ResultBean {
		/**
		 * face_token : f0a1a3881e2e8dc4c30af46a76f12b59
		 * user_list : [{"group_id":"group_hcp","user_id":"370724199312122618","user_info":"桐","score":95.595420837402}]
		 */

		private String face_token;
		private List<UserListBean> user_list;

		public String getFace_token() {
			return face_token;
		}

		public void setFace_token(String face_token) {
			this.face_token = face_token;
		}

		public List<UserListBean> getUser_list() {
			return user_list;
		}

		public void setUser_list(List<UserListBean> user_list) {
			this.user_list = user_list;
		}

		public static class UserListBean {
			/**
			 * group_id : group_hcp
			 * user_id : 370724199312122618
			 * user_info : 桐
			 * score : 95.595420837402
			 */

			private String group_id;
			private String user_id;
			private String user_info;
			private double score;

			public String getGroup_id() {
				return group_id;
			}

			public void setGroup_id(String group_id) {
				this.group_id = group_id;
			}

			public String getUser_id() {
				return user_id;
			}

			public void setUser_id(String user_id) {
				this.user_id = user_id;
			}

			public String getUser_info() {
				return user_info;
			}

			public void setUser_info(String user_info) {
				this.user_info = user_info;
			}

			public double getScore() {
				return score;
			}

			public void setScore(double score) {
				this.score = score;
			}
		}
	}
}
