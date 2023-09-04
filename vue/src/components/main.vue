<template>
	<div id="container">
		<div id="sideBarContainer">
			<el-menu
				default-active="2"
				class="el-menu-vertical-demo sidebar"
				:collapse="isCollapse"
				@open="handleOpen"
				@close="handleClose"
				@select="handleSelect"
			>


				<el-menu-item v-for="(item, index) in dataList" :index=index>
					<template #title>{{item.articleTitle}}</template>
				</el-menu-item>

				
			</el-menu>

		</div>

		<div id="mainContainer">
			<v-md-editor
				id="markdownArea"
				v-model="markdownText"
				mode="preview"
				height="100%"
				default-show-toc="true"
				@copy-code-success="handleCopyCodeSuccess"
			>
			</v-md-editor>
		</div>

	</div>
</template>

<script>
import axios from "axios";
export default {
	components: { axios },
	data() {
		return {
			isCollapse: false,
			markdownText:"null",
			ListLength:null,
			dataList:[],
			
		};
	},
	methods: {
		loadData() {
			axios({
				url: "http://localhost:8080/getArticleList",
				method: "get",
				params: {},
			})
			.then((response) => {
				console.log(response.data);
				this.ListLength = response.data.ListLength;
				this.dataList = response.data.articleList;
				this.markdownText = "# Hello world !"
				// localStorage.setItem("text",response.data.articleList[0].articleContent)
				// console.log(this.markdownText)
			})
			.catch((error) => {
				console.log(error);
			});
		},
		handleSelect(index)
		{
			this.markdownText = this.dataList[index].articleContent;
		}
	},
	mounted(){
		this.loadData()
	}
};
</script>

<style scoped>
#container {
	background-color: bisque;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: space-between;
}

#sideBarContainer {
	/* background-color: gray; */
	width: 200px;
	height: 100%;
}

#mainContainer {
	background-color: gray;
	flex: 1;
	height: 100%;
}

#markdownArea {
	width: 100%;
	height: 500px;
}

.sidebar {
	height: 100%;
}
</style>
