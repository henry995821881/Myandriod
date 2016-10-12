package com.henry.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.henry.myweibo.R;
import com.henry.myweibo.WeiboBody;
import com.sina.weibo.sdk.openapi.models.Status;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Stringutil {

	/**
	 * 
	 * 
	 * 截取<a />标签微博的source 
	 * @param source
	 * @return string
	 */

	public static String getSimpleSource(String source) {

		if (source == null || "".equals(source.trim())) {

			return "";
		}

		String[] splits = source.split(">");

		return splits[1].substring(0, splits[1].length() - 3);
	}

	public static SpannableString getContentString(final Context context,
			TextView tView, String source) {
		String regexAt = "@[\u4e00-\u9fa5\\w]+";
		String regexTopic = "#[\u4e00-\u9fa5\\w]+#";
		String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";
		String regUrl = "http://t.cn/w+";

	
			
		String regex = "(" + regexAt + ")|(" + regexTopic + ")|(" + regexEmoji
				+ ")|(" + regUrl + ")";
		// 将原字符串转化成扩展性字符串
		SpannableString spannableString = new SpannableString(source);
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regex);
		// 是否匹配表达式
		Matcher matcher = pattern.matcher(spannableString);
		
		
		if (matcher.find()) {
			tView.setMovementMethod(LinkMovementMethod.getInstance());
			matcher.reset();
		}

		// 匹配到子串matcher.find()
		while (matcher.find()) {
			final String atString = matcher.group(1);
			final String topicString = matcher.group(2);
			final String emojiString = matcher.group(3);
			final String urlString = matcher.group(4);

			/**
			 * 
			 * 
			 * 
			 * 
			 */
			if (atString != null) {
				int start = matcher.start(1);
				MyClickableSpan clickableSpan = new MyClickableSpan() {

					@Override
					public void updateDrawState(TextPaint ds) {
					
						super.updateDrawState(ds);
						ds.setColor(context.getResources().getColor(R.color.henry_Textpaint_color));
						ds.setUnderlineText(false);
						
					}
					@Override
					public void onClick(View arg0) {
						//Toast.makeText(context, atString, 1).show();

						 String string = arg0.getTag().toString();
						 //Toast.makeText(context, string, 1).show();
						if(string != null){
							
							Intent intent = new Intent(context,WeiboBody.class);
							intent.putExtra("status", string);
						    context.startActivity(intent);
						}
						
						
					}
				};
			
			
				spannableString.setSpan(clickableSpan, start,
						start + atString.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}

			/**
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			if (topicString != null) {
				int start = matcher.start(2);
				MyClickableSpan clickableSpan = new MyClickableSpan() {
					

					@Override
					public void updateDrawState(TextPaint ds) {
						// TODO Auto-generated method stub
						super.updateDrawState(ds);
						
						ds.setColor(context.getResources().getColor(R.color.henry_Textpaint_color));
						ds.setUnderlineText(false);
					}
					@Override
					public void onClick(View arg0) {
						
						//Toast.makeText(context, topicString, 1).show();

					}
				};
				spannableString.setSpan(clickableSpan, start, start
						+ topicString.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			
			/**
			 * 
			 * 
			 * 
			 * 
			 * 
			 */

			if (emojiString != null) {
				int start = matcher.start(3);
				//int emojiName = EmotionUtil.getEmojiByName(emojiString);

				int end = start + emojiString.length();
				
				Bitmap bitmap = BitmapFactory.decodeResource(
						context.getResources(),R.drawable.emoji_0x1f319 );

				if (bitmap != null) {
					int size = (int) tView.getTextSize();
					// 图片压缩
					bitmap = Bitmap
							.createScaledBitmap(bitmap, size, size, true);

					ImageSpan imageSpan = new ImageSpan(context, bitmap);
					spannableString.setSpan(imageSpan, start, end,
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}
			
			
			
			
			/**
			 * 
			 * 
			 */
			if (urlString != null) {
				int start = matcher.start(4);
				MyClickableSpan clickableSpan = new MyClickableSpan() {
					

					@Override
					public void updateDrawState(TextPaint ds) {
						// TODO Auto-generated method stub
						super.updateDrawState(ds);
						
						ds.setColor(context.getResources().getColor(R.color.henry_Textpaint_color));
						ds.setUnderlineText(false);
					}
				
				};
				spannableString.setSpan(clickableSpan, start, start
						+ urlString.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}

		
		}
		
		
		
		
		return spannableString;

	}
	
	
	

	static class MyClickableSpan extends ClickableSpan {

		
		
		@Override
		public void onClick(View arg0) {

		}

		@Override
		public void updateDrawState(TextPaint ds) {
			
			super.updateDrawState(ds);
		
			
		}
	}

}
