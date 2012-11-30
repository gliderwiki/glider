<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
  textarea { height:45px; }
  </style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 var submitEl = $("td :submit")
	      .parent('td')
	      .css({background:"yellow", border:"3px red solid"})
	    .end();
	    
	    $('#result').text('jQuery matched ' + submitEl.length + ' elements.');

	    // so it won't submit
	    $("form").submit(function () { return false; });
	    
	    // Extra JS to make the HTML easier to edit (None of this is relevant to the ':submit' selector
	    $('#exampleTable').find('td').each(function(i, el) {
	        var inputEl = $(el).children(),
	            inputType = inputEl.attr('type') ? ' type="' + inputEl.attr('type') + '"' : '';
	        $(el).before('<td>' + inputEl[0].nodeName + inputType + '</td>');
	    });
	 });
</script>
</head>
<body>
	
	<h2 class="title"> :submit Selector </h2>
	
	<form>
<table id="exampleTable" border="1" cellpadding="10" align="center">

    <tr>
        <th>
            Element Type
        </th>
        <th>
            Element
        </th>

    </tr>
    <tr>
        <td>
            <input type="button" value="Input Button"/>
        </td>

    </tr>
    <tr>
        <td>
            <input type="checkbox" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="file" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="hidden" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="image" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="password" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="radio" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="reset" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="submit" />
        </td>

    </tr>
    <tr>
        <td>
            <input type="text" />
        </td>

    </tr>
    <tr>
        <td>
            <select><option>Option</option></select>
        </td>

    </tr>
    <tr>
        <td>
            <textarea></textarea>
        </td>
    </tr>

    <tr>
        <td>
            <button>Button</button>
        </td>
    </tr>
    <tr>
        <td>
            <button type="submit">Button type="submit"</button>
        </td>
    </tr>

</table>
</form>


	<pre>
		var submitEl = $("td :submit")
	      .parent('td')
	      .css({background:"yellow", border:"3px red solid"})
	    .end();
	    
	    $('#result').text('jQuery matched ' + submitEl.length + ' elements.');

	    // so it won't submit
	    $("form").submit(function () { return false; });
	    
	    // Extra JS to make the HTML easier to edit (None of this is relevant to the ':submit' selector
	    $('#exampleTable').find('td').each(function(i, el) {
	        var inputEl = $(el).children(),
	            inputType = inputEl.attr('type') ? ' type="' + inputEl.attr('type') + '"' : '';
	        $(el).before(td태그' + inputEl[0].nodeName + inputType + 'td태그');
	    });
	</pre>

	<p class="txt">
	 코멘트
	</p>
	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>