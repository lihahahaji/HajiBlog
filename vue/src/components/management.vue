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
                    <el-button style="margin-right: 16px" type="success" @click = "newArticle"> New </el-button>
					<el-button style="margin-right: 16px" type="danger" @click = "quit"> Quit </el-button>
				</div>
			</div>
		</div>

		<div id="mainContainer">
			<el-table :data="filteredData" style="width: 90%">
				<el-table-column label="ID" prop="articleId" />
				<el-table-column label="Title" prop="articleTitle" />
				<el-table-column align="right">
					<template #header>
						<el-input
							v-model="search"
							size="small"
							placeholder="Type to search"
						/>
					</template>
					<template #default="scope">
						<el-button size="small" @click="handleEdit(scope.$index, scope.row)"
							>Edit</el-button
						>
						<el-button
							size="small"
							type="danger"
							@click="handleDelete(scope.$index, scope.row)"
							>Delete</el-button
						>
					</template>
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>

import axios from "axios";
export default {
    component:{
        axios
    },
	data() {
		return {
			search: "",
			tableData: [],
		};
	},
	computed: {
		filteredData() {
			return this.tableData.filter(
				(data) => !this.search || data.name.toLowerCase().includes(this.search.toLowerCase())
					
			);
		},
	},
    methods:{
        loadData()
        {
            axios({
				url: "http://localhost:8080/getArticleList",
				method: "get",
				params: {},
			})
				.then((response) => {
					console.log(response.data);
					this.ListLength = response.data.ListLength;
					this.tableData = response.data.articleList;
                    console.log(this.tableData)
					// this.markdownText = this.dataList[0].articleContent;
					// localStorage.setItem("text",response.data.articleList[0].articleContent)
					// console.log(this.markdownText)
				})
				.catch((error) => {
					console.log(error);
				});
        },
        quit()
        {
            this.$router.push('/main');
        },
        newArticle(){
            this.$router.push('/edit');
        }
    },
    beforeMount(){
        this.loadData();
    }
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

.headerContainer {
	width: 100%;
	height: 50px;
	padding-left: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #ccc;
}

#mainContainer {
	display: flex;
	flex-direction: column;
	flex: 1;
	/* background-color: #f5f5f5; */
    justify-content: top;
    align-items: center;
}
</style>
