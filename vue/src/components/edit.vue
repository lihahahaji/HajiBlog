<template>
	<div id="container">
		<v-md-editor
			id="markdownArea"
			v-model="markdownText"
			height="100%"
			default-show-toc="true"
			@save="saveFile"
		>
		</v-md-editor>
	</div>
</template>

<script>
import { ElMessage } from "element-plus";
import axios from "axios";
export default {
	components: {
		ElMessage,
	},
	data() {
		return {
			markdownText: "",
			title: "",
		};
	},
	methods: {
		saveFile() {
			console.log(this.markdownText);
			ElMessage({
				message: "保存成功.",
				type: "success",
			});

			// 处理文章内容 使用第一行作为文章的标题
			this.title = this.markdownText.split("\n")[0].replace(/#\s*/g, "");
			// console.log(this.title);

			// 判断是新文章还是编辑旧文章
			if (localStorage.getItem("editMode") == "new") {
				// 新文章
				axios({
					url: "http://localhost:8080/newBlog",
					method: "post",
					params: {
						articleTitle: this.title,
						articleContent: this.markdownText,
					},
				})
					.then((response) => {
						console.log(response.data);
						// 跳转到管理页面
						this.$router.push("/management");
					})
					.catch((error) => {
						console.log(error);
					});
			} else if (localStorage.getItem("editMode") == "update") {
				// 编辑旧文章
				axios({
					url: "http://localhost:8080/editBlog",
					method: "post",
					params: {
						articleId: localStorage.getItem("articleId"),
						articleTitle: this.title,
						articleContent: this.markdownText,
					},
				})
					.then((response) => {
						console.log(response.data);
						// 跳转到管理页面
						this.$router.push("/management");
					})
					.catch((error) => {
						console.log(error);
					});
			}
		},
		loadContent() {
			// 当此时chuck为update时，说明是修改文章内容，此时需要从localStorage中获取文章内容
			if (localStorage.getItem("editMode") == "update") {
				this.markdownText = localStorage.getItem("articleContent");
			}
		},
	},
	beforeMount() {
		this.loadContent();
	},
};
</script>

<style scoped>
#container {
	/* background-color: bisque; */
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
}
</style>
