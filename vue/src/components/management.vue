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
					<el-button style="margin-right: 16px" type="danger"> Quit </el-button>
				</div>
			</div>
		</div>

		<div id="mainContainer">
			<el-table :data="filteredData" style="width: 90%">
				<el-table-column label="ID" prop="date" />
				<el-table-column label="Title" prop="name" />
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
export default {
	data() {
		return {
			search: "",
			tableData: [
				{
					date: "2016-05-03",
					name: "Tom",
					address: "No. 189, Grove St, Los Angeles",
				},
				{
					date: "2016-05-02",
					name: "John",
					address: "No. 189, Grove St, Los Angeles",
				},
				{
					date: "2016-05-04",
					name: "Morgan",
					address: "No. 189, Grove St, Los Angeles",
				},
				{
					date: "2016-05-01",
					name: "Jessy",
					address: "No. 189, Grove St, Los Angeles",
				},
			],
		};
	},
	computed: {
		filteredData() {
			return this.tableData.filter(
				(data) => !this.search || data.name.toLowerCase().includes(this.search.toLowerCase())
					
			);
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
	background-color: #f5f5f5;
    justify-content: top;
    align-items: center;
}
</style>
