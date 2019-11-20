package com.study.apphcpzj.baidu.bean;

/**
 * @author ztt : 2019-03-26
 */
public class SfzBean extends BaseBean{


	/**
	 * direction : 0
	 * image_status : normal
	 * idcard_type : normal
	 * edit_tool : Adobe Photoshop CS3 Windows
	 * words_result : {"住址":{"location":{"left":267,"top":453,"width":459,"height":99},"words":"南京市江宁区弘景大道3889号"},"公民身份号码":{"location":{"left":443,"top":681,"width":589,"height":45},"words":"330881199904173914"},"出生":{"location":{"left":270,"top":355,"width":357,"height":45},"words":"19990417"},"姓名":{"location":{"left":267,"top":176,"width":152,"height":50},"words":"伍云龙"},"性别":{"location":{"left":269,"top":262,"width":33,"height":52},"words":"男"},"民族":{"location":{"left":492,"top":279,"width":30,"height":37},"words":"汉"}}
	 * words_result_num : 6
	 */

	private int direction;
	private String image_status;
	private String idcard_type;
	private String edit_tool;
	private WordsResultBean words_result;
	private int words_result_num;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getImage_status() {
		return image_status;
	}

	public void setImage_status(String image_status) {
		this.image_status = image_status;
	}

	public String getIdcard_type() {
		return idcard_type;
	}

	public void setIdcard_type(String idcard_type) {
		this.idcard_type = idcard_type;
	}

	public String getEdit_tool() {
		return edit_tool;
	}

	public void setEdit_tool(String edit_tool) {
		this.edit_tool = edit_tool;
	}

	public WordsResultBean getWords_result() {
		return words_result;
	}

	public void setWords_result(WordsResultBean words_result) {
		this.words_result = words_result;
	}

	public int getWords_result_num() {
		return words_result_num;
	}

	public void setWords_result_num(int words_result_num) {
		this.words_result_num = words_result_num;
	}

	public static class WordsResultBean {
		/**
		 * 住址 : {"location":{"left":267,"top":453,"width":459,"height":99},"words":"南京市江宁区弘景大道3889号"}
		 * 公民身份号码 : {"location":{"left":443,"top":681,"width":589,"height":45},"words":"330881199904173914"}
		 * 出生 : {"location":{"left":270,"top":355,"width":357,"height":45},"words":"19990417"}
		 * 姓名 : {"location":{"left":267,"top":176,"width":152,"height":50},"words":"伍云龙"}
		 * 性别 : {"location":{"left":269,"top":262,"width":33,"height":52},"words":"男"}
		 * 民族 : {"location":{"left":492,"top":279,"width":30,"height":37},"words":"汉"}
		 */

		private 住址Bean 住址;
		private 公民身份号码Bean 公民身份号码;
		private 出生Bean 出生;
		private 姓名Bean 姓名;
		private 性别Bean 性别;
		private 民族Bean 民族;

		public 住址Bean get住址() {
			return 住址;
		}

		public void set住址(住址Bean 住址) {
			this.住址 = 住址;
		}

		public 公民身份号码Bean get公民身份号码() {
			return 公民身份号码;
		}

		public void set公民身份号码(公民身份号码Bean 公民身份号码) {
			this.公民身份号码 = 公民身份号码;
		}

		public 出生Bean get出生() {
			return 出生;
		}

		public void set出生(出生Bean 出生) {
			this.出生 = 出生;
		}

		public 姓名Bean get姓名() {
			return 姓名;
		}

		public void set姓名(姓名Bean 姓名) {
			this.姓名 = 姓名;
		}

		public 性别Bean get性别() {
			return 性别;
		}

		public void set性别(性别Bean 性别) {
			this.性别 = 性别;
		}

		public 民族Bean get民族() {
			return 民族;
		}

		public void set民族(民族Bean 民族) {
			this.民族 = 民族;
		}

		public static class 住址Bean {
			/**
			 * location : {"left":267,"top":453,"width":459,"height":99}
			 * words : 南京市江宁区弘景大道3889号
			 */

			private LocationBean location;
			private String words;

			public LocationBean getLocation() {
				return location;
			}

			public void setLocation(LocationBean location) {
				this.location = location;
			}

			public String getWords() {
				return words;
			}

			public void setWords(String words) {
				this.words = words;
			}

			public static class LocationBean {
				/**
				 * left : 267
				 * top : 453
				 * width : 459
				 * height : 99
				 */

				private int left;
				private int top;
				private int width;
				private int height;

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
			}
		}

		public static class 公民身份号码Bean {
			/**
			 * location : {"left":443,"top":681,"width":589,"height":45}
			 * words : 330881199904173914
			 */

			private LocationBeanX location;
			private String words;

			public LocationBeanX getLocation() {
				return location;
			}

			public void setLocation(LocationBeanX location) {
				this.location = location;
			}

			public String getWords() {
				return words;
			}

			public void setWords(String words) {
				this.words = words;
			}

			public static class LocationBeanX {
				/**
				 * left : 443
				 * top : 681
				 * width : 589
				 * height : 45
				 */

				private int left;
				private int top;
				private int width;
				private int height;

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
			}
		}

		public static class 出生Bean {
			/**
			 * location : {"left":270,"top":355,"width":357,"height":45}
			 * words : 19990417
			 */

			private LocationBeanXX location;
			private String words;

			public LocationBeanXX getLocation() {
				return location;
			}

			public void setLocation(LocationBeanXX location) {
				this.location = location;
			}

			public String getWords() {
				return words;
			}

			public void setWords(String words) {
				this.words = words;
			}

			public static class LocationBeanXX {
				/**
				 * left : 270
				 * top : 355
				 * width : 357
				 * height : 45
				 */

				private int left;
				private int top;
				private int width;
				private int height;

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
			}
		}

		public static class 姓名Bean {
			/**
			 * location : {"left":267,"top":176,"width":152,"height":50}
			 * words : 伍云龙
			 */

			private LocationBeanXXX location;
			private String words;

			public LocationBeanXXX getLocation() {
				return location;
			}

			public void setLocation(LocationBeanXXX location) {
				this.location = location;
			}

			public String getWords() {
				return words;
			}

			public void setWords(String words) {
				this.words = words;
			}

			public static class LocationBeanXXX {
				/**
				 * left : 267
				 * top : 176
				 * width : 152
				 * height : 50
				 */

				private int left;
				private int top;
				private int width;
				private int height;

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
			}
		}

		public static class 性别Bean {
			/**
			 * location : {"left":269,"top":262,"width":33,"height":52}
			 * words : 男
			 */

			private LocationBeanXXXX location;
			private String words;

			public LocationBeanXXXX getLocation() {
				return location;
			}

			public void setLocation(LocationBeanXXXX location) {
				this.location = location;
			}

			public String getWords() {
				return words;
			}

			public void setWords(String words) {
				this.words = words;
			}

			public static class LocationBeanXXXX {
				/**
				 * left : 269
				 * top : 262
				 * width : 33
				 * height : 52
				 */

				private int left;
				private int top;
				private int width;
				private int height;

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
			}
		}

		public static class 民族Bean {
			/**
			 * location : {"left":492,"top":279,"width":30,"height":37}
			 * words : 汉
			 */

			private LocationBeanXXXXX location;
			private String words;

			public LocationBeanXXXXX getLocation() {
				return location;
			}

			public void setLocation(LocationBeanXXXXX location) {
				this.location = location;
			}

			public String getWords() {
				return words;
			}

			public void setWords(String words) {
				this.words = words;
			}

			public static class LocationBeanXXXXX {
				/**
				 * left : 492
				 * top : 279
				 * width : 30
				 * height : 37
				 */

				private int left;
				private int top;
				private int width;
				private int height;

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
			}
		}
	}
}
