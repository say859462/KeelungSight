$(document).ready(function(){
  $("#Zone-Button-Group button").click(function(){
    let requestURL = 'http://localhost:8080/SightAPI?zone=' + String($(this).attr("value"));

    $.ajax({
        url:requestURL,
        method: 'GET',
        success:function(data){
            console.log("擷取資料成功");
            renderResult(data);
        },
        error:function(){
            alert("抓取資料錯誤");
        }
    })
  });






});

function showGoogleMapInfo(btn){

    let address = btn.value;

  // 使用 encodeURIComponent() 處理地址字串，確保特殊字串正確編碼
  let encodedAddress = encodeURIComponent(address);

  //  Google Map 網址URL
  let mapLink = 'https://www.google.com/maps?q=' + encodedAddress;

  // 跳轉Google Map 網頁
  window.open(mapLink,'_blank');
}

function renderResult(data){
    let container = document.getElementById('landscapes');
    //清空景點資訊
    container.innerHTML = '';

    let tmp = "";
    //新增所有該地區的景點資訊
    for(let i = 0 ;i < data.length ;i++){
        let sightName = data[i].sightName;
        //景點地區
        let zone = data[i].zone;
        //景點類別
        let category = data[i].category;
        //景點圖片網址
        let photoURL = data[i].photoURL;
        //景點描述
        let description = data[i].description;

        //景點地址
        let address = data[i].address;
        tmp = "<div class='col-md-4 col-sm-12 pb-2'>";
        tmp += "<div class='card'>";
        tmp += "<div class='card-body'>";
        tmp += "<h5 class='card-title '>" + sightName +"</h4>";
        tmp += "<p class='card-text text-secondary'>" + zone + "&nbsp"+ category + "</p>";
        tmp += "<button type='button' class='btn btn-outline-primary shadow-sm me-2 ' id='"+ address + "' onclick='showGoogleMapInfo(this)' value='" + sightName + "'>地址</button>";
        tmp += "<button type='button' class='btn btn-outline-primary shadow-sm' data-bs-toggle='collapse' data-bs-target='#description"+ i + "'>詳細資訊</button>";
        tmp += "<p class='collapse mt-4 text-center' id='description" + i + "'> <img src='" + photoURL +"' class='img-fluid rounded-1' >"+ description + "</p>";
        tmp += "</div></div></div>";
        container.innerHTML += tmp;

    }
}
