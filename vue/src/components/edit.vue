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
            title:"",
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
            this.title = this.markdownText.split('\n')[0].replace(/#\s*/g, '');
            // console.log(this.title);

			axios({
				url: "http://localhost:8080/newBlog",
				method: "post",
				params: {
                    articleTitle: this.title,
					articleContent: this.markdownText,
                },
			})
				.then((response) => {
					console.log(response.data);w

				})
				.catch((error) => {
					console.log(error);
				});

			// this.$router.push("/main");
		},
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
