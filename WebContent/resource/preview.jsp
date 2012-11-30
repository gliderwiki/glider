<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>
    <style type="text/css">
        .preView { width: 70px; height: 70px; text-align: center; border:1px solid silver; }
    </style>
    <script type="text/javascript">

        function fileUploadPreview(thisObj, preViewer) {
            if(!/(\.gif|\.jpg|\.jpeg|\.png)$/i.test(thisObj.value)) {
                alert("이미지 형식의 파일을 선택하십시오");
                return;
            }

            preViewer = (typeof(preViewer) == "object") ? preViewer : document.getElementById(preViewer);
            var ua = window.navigator.userAgent;

            if (ua.indexOf("MSIE") > -1) {
                var img_path = "";
                if (thisObj.value.indexOf("\\fakepath\\") < 0) {
                    img_path = thisObj.value;
                } else {
                    thisObj.select();
                    var selectionRange = document.selection.createRange();
                    img_path = selectionRange.text.toString();
                    thisObj.blur();
                }
                preViewer.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='fi" + "le://" + img_path + "', sizingMethod='scale')";
            } else {
                preViewer.innerHTML = "";
                var W = preViewer.offsetWidth;
                var H = preViewer.offsetHeight;
                var tmpImage = document.createElement("img");
                preViewer.appendChild(tmpImage);

                tmpImage.onerror = function () {
                    return preViewer.innerHTML = "";
                }

                tmpImage.onload = function () {
                    if (this.width > W) {
                        this.height = this.height / (this.width / W);
                        this.width = W;
                    }
                    if (this.height > H) {
                        this.width = this.width / (this.height / H);
                        this.height = H;
                    }
                }
                if (ua.indexOf("Firefox/3") > -1) {
                    var picData = thisObj.files.item(0).getAsDataURL();
                    tmpImage.src = picData;
                } else {
                    tmpImage.src = "file://" + thisObj.value;
                }
            }
        }

    </script>
</head>
<body>
    <input id="fileData" name="fileData" type="file" onchange="fileUploadPreview(this, 'preView')" />
    <div id="preView" class="preView" title="이미지미리보기"></div>
</body>
</html>
