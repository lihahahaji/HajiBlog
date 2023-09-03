<template>
	<div id="container">
		<div id="sideBarContainer">
			<el-menu
				default-active="2"
				class="el-menu-vertical-demo sidebar"
				:collapse="isCollapse"
				@open="handleOpen"
				@close="handleClose"
			>
				<el-sub-menu index="1">
					<template #title>
						<el-icon><location /></el-icon>
						<span>Navigator One</span>
					</template>

					<el-menu-item-group>
						<template #title><span>Group One</span></template>
						<el-menu-item index="1-1">item one</el-menu-item>
						<el-menu-item index="1-2">item two</el-menu-item>
					</el-menu-item-group>

					<el-menu-item-group title="Group Two">
						<el-menu-item index="1-3">item three</el-menu-item>
					</el-menu-item-group>

					<el-sub-menu index="1-4">
						<template #title><span>item four</span></template>
						<el-menu-item index="1-4-1">item one</el-menu-item>
					</el-sub-menu>
				</el-sub-menu>

				<el-menu-item index="2">
					<el-icon><icon-menu /></el-icon>
					<template #title>Navigator Two</template>
				</el-menu-item>
				<el-menu-item index="3" disabled>
					<el-icon><document /></el-icon>
					<template #title>Navigator Three</template>
				</el-menu-item>
				<el-menu-item index="4">
					<el-icon><setting /></el-icon>
					<template #title>Navigator Four</template>
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
			markdownText:localStorage.getItem("text"),
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
				// this.markdownText = response.data.articleList[0].articleContent;
				// localStorage.setItem("text",response.data.articleList[0].articleContent)
				// console.log(this.markdownText)
			})
			.catch((error) => {
				console.log(error);
			});
		},
		handleCopyCodeSuccess()
		{
			console.log("复制成功")
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
