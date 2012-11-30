/*------------------------------------------------------------------
Project: jquery Graph plugin
Last change: 29.3.2012
Scripted by: Si Young Jung, cikandin@gmail.com http://catntuna.tistory.com
Works in browsers: FF 3.5+, Opera 10.5+, Safari 4+, Google Chrome 4.0+

pieChart(data,size,formation);

Using Example : 

  data = [
	['pepper',7],
	['nange',3],
	['ggari',9],
	['cabbage',5],
	['plum',3],
	['gaji',8],
	['buchu',3],
	['pumpkin',3],
];

$('#target').pieChart(data,300,"pie");
-------------------------------------------------------------------*/

(function( $ ) {
	jQuery.fn.pieChart = function(data,size,formation,title){
		//variables
		var numberof = data.length;
		var sum = 0;
		var angle = 0;
		var perc = [];
		var temp = [];
		var color = ["#BC3603","#D97904","#F2B705","#86AD00","#0092B9","#a461c4","#eeeeee","#9f9f9f","#7b6150","#024167"];
		var color2 = ["#de3d00","#fb8a00","#ffc517","#9bc800","#00addb","#b986d2","#f3f3f3","#b9b9b9","#ae9483","#04639f"];
		//calculate
			for(i = 0; i < numberof; i++){
				sum = sum+ data[i][1];
				temp[i] = data[i][1];
			}
			for(i = 0; i < numberof; i++){
				perc[i] = data[i][1]/sum*360;
			}
			
		//when pie form
		if(formation == "pie"){
			//chart exist
			if(this.find('.pieChart_pie_chart').length){
						this.find('.pieChart_legend_data').text('');
						this.find('.pieChart_legend_percent').text('');
						this.find('.pieChart_legend_span').css({'background':'none'});
						this.find('ul div').css({
																 	'-moz-transform': 'rotate(0deg)',
																 	'-webkit-transform': 'rotate(0deg)',
																 	'-o-transform': 'rotate(0deg)'
																 	});
			}else{
			this.append('<div class = "pieChart_title" style="position:absolute;left:25px;font-size:14px"></div>');
			this.append('<div class="pieChart_pie_chart" style="position:relative;top:35px;"><ul></ul></div>');
			this.append('<div class = "pieChart_label" style = "margin:'+(size/20+25)+'px 0 0 25px;position:relative;float:left;"><div class="pieChart_legend"><ul style="list-style-type: none; margin: 0; padding: 0; font-size: 12px;color: #333333;"></ul></div><div class = "pieChart_mouseover"></div></div>');
			}
			var chart_box = this.find('.pieChart_pie_chart');
			var chart_box_ul = chart_box.find('ul');
			//add title
			this.find('.pieChart_title').text(title);
			//cal
			for(i = 0; i < numberof ; i++){
			 var c = numberof - i - 1;
			 if(this.find('.pieChart_c'+i+'_r').length){
			 }else{
			//make legend
			 this.find('.pieChart_legend ul').append('<li style = "display: block;margin: 0;padding: 0;"><span class = "pieChart_legend_span" style="display: block; float: left; width:8px; height: 8px; margin: 3px 5px 0 0; background: #FFFFFF;"></span><span class = "pieChart_legend_data"></span><span class =  "pieChart_legend_percent" style="margin-left:8px"></span></li>');
				//create graph
			 chart_box_ul.append('<li class="pieChart_c'+i+'_r"><div><span class="pie_left"></span></div></li><li class="pieChart_c'+i+'_l"><div><span class="pie_right"></span></div></li>');
			 }
			   //graph style
			 this.find('.pieChart_c'+i+'_l span').css({
			 											'background': color[c],
			 											'background-image': '-moz-radial-gradient(right 45deg, circle closest-corner, '+color2[c]+' 10%, '+color[c]+' 100%)',
			 											'background-image': '-webkit-gradient(radial, left center, 10, left center, '+size/2+', from('+color2[c]+'), to('+color[c]+'))'
			 											});
			 this.find('.pieChart_c'+i+'_r span').css({
														'background': color[c],
														'background-image': '-moz-radial-gradient(left 45deg, circle closest-corner, '+color2[c]+' 10%, '+color[c]+' 100%)',
														'background-image': '-webkit-gradient(radial, right center, 10, right center, '+size/2+', from('+color2[c]+'), to('+color[c]+'))'
														});
														
				//legend
				var legend_data = this.find('.pieChart_legend_data').eq(i); //cannot use :eq() 
				var legend_percent = this.find('.pieChart_legend_percent').eq(i);
				var legend_span = this.find('.pieChart_legend_span').eq(i);
				//string cal
				if(data[i][0].length>16){
					legend_data.text(data[i][0].substr(0,14)+"..")}else{
					legend_data.text(data[i][0])
				}
				legend_percent.text(Math.round(perc[i]/360*100)+'%');
				legend_span.css({'background':color[i]});
			}
			//mouseover
			var temp_label = this.find('.pieChart_mouseover');
			var temp_this = this.find('.pieChart_legend ul');
			this.find('.pieChart_legend_data').hover(function(){
						var idx = jQuery(this).parent('li').index();
						var pos = jQuery(this).position();
						temp_label.fadeIn('fast');
						temp_label.text(data[idx][0]+" ");
						temp_label.append('<font color="'+color[0]+'"><b>'+Math.round(perc[idx]/360*100)+'%</b></font>');
						temp_label.css({
										'right':135,
										'top':pos.top,
						});
						
			},function(){
						temp_label.hide();
			});
			//css
			chart_box.css({
								'position':'relative',
								'float':'left',
								width: size,
								height: size,
								'margin': '0px 0px 0px 230px',
								'padding': 0
							});
			chart_box_ul.css({
								'list-style-type': 'none',
								width: size,
								height: size,
								'padding': 0,
								'margin': 0
							});
			chart_box.find('li').css({
								'position': 'absolute',
								'top': '0px',
								width: size,
								height: size,
								'padding': 0,
								'margin': 0
							});
			chart_box.find('div').css({
							//	'position': 'absolute',
								width: size,
								height: size,
								'padding': 0,
								'margin': 0
							});
			chart_box.find('li div').css({'-webkit-transition': 'all .7s ease-out','-moz-transition': 'all .7s ease-out',});

			chart_box.find('span').css({'display':'block',width:size/2,height:size});

			chart_box.find('ul li:nth-child(odd)').css({'clip': 'rect(0px, '+size+'px, '+size+'px, '+size/2+'px)'});
			
			chart_box.find('ul li:nth-child(even)').css({'clip': 'rect(0px, '+size/2+'px, '+size+'px, 0px)'});
			
			this.find('.pie_left').css({
							'-moz-border-radius-topleft': size/2+'px',
							'-moz-border-radius-bottomleft': size/2+'px',
							'-webkit-border-top-left-radius': size/2+'px',
							'-webkit-border-bottom-left-radius': size/2+'px',
							'border-top-left-radius': size/2+'px',
							'border-bottom-left-radius': size/2+'px'
						});
			this.find('.pie_right').css({
				'margin-left': size/2+'px',
				'-moz-border-radius-topright': size/2+'px',
				'-moz-border-radius-bottomright': size/2+'px',
				'-webkit-border-top-right-radius': size/2+'px',
				'-webkit-border-bottom-right-radius': size/2+'px',
				'border-top-right-radius': size/2+'px',
				'border-bottom-right-radius': size/2+'px'
			});
			this.find('.pieChart_mouseover').css({
				'position':'absolute',
				'display':'none',
				'text-align':'center',
				//'border-radius':'4px',
				'border':'#ccc 1px solid',
				'padding':'4px',
				'font-size':'12px',
				'background':'#fff',
			});
			//cal chart
			for(i=0; i< numberof; i++){
				var max = numberof*2-1
				var number = max-(i*2);
				var number2 = max-(i*2+1);
				var division = chart_box.find('ul div');
				angle = angle+perc[i];				
				
				if(angle>=180){
					var angle2 = angle-180;
						division.eq(number2).show().css({
																 	'-moz-transform': 'rotate(180deg)',
																 	'-webkit-transform': 'rotate(180deg)',
																 	'-o-transform': 'rotate(180deg)'
																 	});
						division.eq(number).show().css({
																 	'-moz-transform': 'rotate('+angle2+'deg)',
																 	'-webkit-transform': 'rotate('+angle2+'deg)',
																 	'-o-transform': 'rotate('+angle2+'deg)'
																 	});									 											 	
				}else{
						division.eq(number2).show().css({
																 	'-moz-transform': 'rotate('+angle+'deg)',
																 	'-webkit-transform': 'rotate('+angle+'deg)',
																 	'-o-transform': 'rotate('+angle+'deg)'
																 	});
						division.eq(number).show().css({
																 	'-moz-transform': 'rotate(0deg)',
																 	'-webkit-transform': 'rotate(0deg)',
																 	'-o-transform': 'rotate(0deg)'
																 	});											 	
				}
			
			}
		}
		if(formation == "bar"){
					var chart_box;
					//create background
						if(this.find('.pieChart_bar_chart').length){
							chart_box = this.find('.pieChart_bar_chart');
							chart_box.find('dt').text('');
							chart_box.find('table td div').text('');
							chart_box.find('dd span').text('');
							chart_box.find('dd span').css({width:0,});
						}else{
						this.append('<div class = "pieChart_bar_chart" style="position:relative; margin: 0px 0px 0px 100px;"><div class = "pieChart_title" style="position:absolute;left:170px;font-size:14px"></div><div class = "pieChart_bar" style="top:25px;position:relative;"><dl></dl></div><div class = "pieChart_number"><div></div></div><div class = "pieChart_mouseover"></div>');
						//create legend
						/*
						this.append('<div class = "pieChart_label" style = "margin:'+size/10+'px 0 0 25px;position:relative;float:left;"><div class="pieChart_legend"><ul style="list-style-type: none; margin: 0; padding: 0; font-size: 12px;color: #333333;"></ul></div></div>');
						*/
						this.find('.pieChart_bar').append('<table><tr><td></td><td></td><td></td><td></td></tr></table>');
						}
						chart_box = this.find('.pieChart_bar_chart');
						//add title
						this.find('.pieChart_title').text(title);
						//put box	
						for(i = 0; i < numberof ; i++){
							if (chart_box.find('dt').eq(i).length) {
							
							}else{
							//create legend
							/*
							this.find('.pieChart_legend ul').append('<li style = "display: block;margin: 0;padding: 0;"><span class = "pieChart_legend_span" style="display: block; float: left; width:8px; height: 8px; margin: 3px 5px 0 0; background: #FFFFFF;"></span><span class = "pieChart_legend_data"></span><span class =  "pieChart_legend_percent" style="margin-left:8px"></span></li>');
							*/
							//create box
							chart_box.find('dl').append("<dt></dt><dd><span class='data-one'></span></dd>").css({'position':'relative'});
							}
							/*
							chart_box.find('dd span').eq(i).css({
															'background':color[i],
															'background':'-webkit-gradient(linear, left top,left bottom, from('+color[i]+'), to('+color2[i]+'))',
													
							});
							*/
							chart_box.find('dd span').eq(i).css({
															'background':color[4],
															'background-image':'-moz-linear-gradient(90deg, '+color[4]+', '+color2[4]+')',
															'background-image':'-webkit-gradient(linear, left top,left bottom, from('+color[4]+'), to('+color2[4]+'))',
							});
							if(data[i][0].length>16){
								chart_box.find('dt').eq(i).text(data[i][0].substr(0,14)+"..")}else{
								chart_box.find('dt').eq(i).text(data[i][0]);
							}
							
						}
					//mouseover
					var temp_label = this.find('.pieChart_mouseover');
					chart_box.find('dt').hover(function(){
								var idx = jQuery(this).index()/2;
								var pos = jQuery(this).position();
								temp_label.fadeIn('fast');
								temp_label.text(data[idx][0]);
								temp_label.css({
												'left':165,
												'top':pos.top+35,
								});
								
					},function(){
								temp_label.hide();
					});
					//background css
					chart_box.css({
									'float':'left',
									'position':'relative',
					});
					
					chart_box.find('table').css({
												width:size,
												height: (20)*numberof,
												'border-left':'1px solid #999',
												'position':'absolute',
												'margin-left':'168px',
												'z-index':'-1',
												'top':'-4px',
					});
					chart_box.find('td').css({
												'border-right':'1px solid #999',
												'position':'relative',
					});
					
					chart_box.find('.pieChart_bar').css({
//															width:size+150,
					});
					chart_box.find('dt').css({
						width:158,
						'text-align':'right',
						'font-size':'12px',
						'clear':'both',
						'float':'left',
						'margin':'0 0 5px 0',
						'padding':'0',
						'display':'inline-block',
					});
					chart_box.find('dd').css({
						width:size-4,
						'float':'left',
						'display':'inline',
						'margin':'0 10px 0',
						'padding':'0',
					});
					chart_box.find('dd span').css({
						'-webkit-transition': 'all .7s ease-out',
						'-moz-transition': 'all .7s ease-out',
						height:14,
						'margin-top':-1,
						'color':'#eee',
						'font-size':'12px',
						'text-align':'center',
						'display': 'block',
						'text-shadow':'1px 1px 1px rgba(0,0,0,0.6)',
						  '-moz-border-radius':'2px',
						  '-webkit-border-radius':'2px',
						  'border-radius':'2px',
						  //'-webkit-box-reflect':'below 0 -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,0)), to(rgba(0,0,0,0.25)))',
					});
					this.find('.pieChart_number').css({
									'clear':'both',
									width:size,
									height: 15,
									'position':'relative',
									'margin-left':'128px',
					});
					this.find('.pieChart_mouseover').css({
						'position':'absolute',
						'display':'none',
						'text-align':'center',
						//'border-radius':'4px',
						'border':'#ccc 1px solid',
						'padding':'4px',
						'font-size':'12px',
						'background':'#fff',
					});
			//put num
			var minmax = function(array,minmax){
				var temp;
				for(i=0 ; i<array.length ; i++){
					if(temp){
						if(minmax=="max"){
							if(temp<=array[i]){
								temp = array[i];
							}
						}
						if(minmax=="min"){
							if(temp>=array[i]){
								temp = array[i];
							}
						}
					}else{
						temp = array[i];
					}
				}
				return temp;
			}
			var max = minmax(temp,"max");
//			var min = minmax(temp,"min");
			var pow = Math.pow(10,max.toString().length-1);
			var max_length = max/pow;
			max_length = max_length.toFixed(1);
			max_length = (parseFloat(max_length)+0.1)*pow;
			//put num to table
					this.find('.pieChart_number').append('<div></div>');
					this.find('.pieChart_number div').eq(0).css({
													width:size/5,
													'text-align':'center',
													'position':'absolute',
													'left':40-size/10,
													'bottom':'-40px',
													'font-size':'13px',
													'color':'#999',
					});
					this.find('.pieChart_number div').eq(0).text('0');
				for(i = 1; i < 5; i++){
					this.find('.pieChart_number').append('<div></div>');
					this.find('.pieChart_number div').eq(i).css({
													width:size/5,
													'text-align':'center',
													'position':'absolute',
													'left':size/4*(i-0.02)+'px',
													'bottom':'-40px',
													'font-size':'13px',
													'color':'#999',
					});
					this.find('.pieChart_number div').eq(i).text(Math.round(max_length/4*(i+1)));
				}
			//put number
				for(i = 0; i < numberof; i++){
					chart_box.find('dd span').eq(i).show().css({
								width:data[i][1]/max_length*100+'%',
					}).append(data[i][1]);
				}
		}
	};
})(jQuery);