<template>
	<div id="container">
		<div>
			<div class="headerContainer">
				<div id="headerLeft">
					<img
						src="/HajiBlog.png"
						style="width: 30px; height: 30px; margin-right: 10px"
					/>
					<h3>HajiBlog</h3>
				</div>

				<div id="headerRight">
					
					<el-button type="primary">New</el-button>
				</div>
			</div>
		</div>

		<div id="mainAndSiderContainer">
			<div id="sideBarContainer">
				<el-menu
					default-active="2"
					class="el-menu-vertical-demo sidebar"
					:collapse="isCollapse"
					@open="handleOpen"
					@close="handleClose"
					@select="handleSelect"
				>
					<el-menu-item v-for="(item, index) in dataList" :index="index">
						<template #title>{{ item.articleTitle }}</template>
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
	</div>
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
	components: {
		axios,
		ElMessage,
	},
	data() {
		return {
			isCollapse: false,
			markdownText: "",
			ListLength: null,
			dataList: [],
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
					// this.markdownText = this.dataList[0].articleContent;
					// localStorage.setItem("text",response.data.articleList[0].articleContent)
					// console.log(this.markdownText)
				})
				.catch((error) => {
					console.log(error);
				});
		},
		handleSelect(index) {
			this.markdownText = this.dataList[index].articleContent;
		},
		handleCopyCodeSuccess() {
			ElMessage({
				message: "复制成功",
				type: "success",
			});
		},
	},
	mounted() {
		this.loadData();
	},
};
</script>

<style scoped>
#headerLeft {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-left: 20px;
}

#headerRight {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-right: 20px;
}

#container {
	/* background-color: bisque; */
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
}

#mainAndSiderContainer {
	/* background-color: bisque; */
	width: 100%;
	flex: 1;
	display: flex;
	justify-content: space-between;
}

.headerContainer {
	width: 100%;
	height: 50px;
	padding-left: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #ccc;
}

#sideBarContainer {
	/* background-color: gray; */
	width: 200px;
	height: 100%;
}

#mainContainer {
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
