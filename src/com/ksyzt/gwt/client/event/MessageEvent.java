package com.ksyzt.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MessageEvent extends GwtEvent<MessageHandler> {

	public static Type<MessageHandler> TYPE = new Type<MessageHandler>();

	@Override
	protected void dispatch(MessageHandler handler) {
		handler.onMessage(this.getSource(), message, value);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MessageHandler> getAssociatedType() {
		return TYPE;
	}

	Integer message;
	Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Integer getMessage() {
		return message;
	}

	public void setMessage(Integer message) {
		this.message = message;
	}

	public MessageEvent(Integer message, Object value) {
		this.message = message;
		this.value = value;

	}

	public static final Integer CLOSE_AND_REFRESH = 1;
	public static final Integer CLOSE = 2;
	public static final Integer CANCEL = 3;
	public static final Integer CHILDCLICK = 4;
	public static final Integer CREATEROOT = 5;
	public static final Integer ITEMCLICK = 6;
	public static final Integer SELECTUSER = 7;
	public static final Integer CONTEXTMENU = 8;
	public static final Integer DELETE = 9;
	public static final Integer EDIT = 10;
	public static final Integer UPDATE = 11;
	public static final Integer REFRESH = 12;
	public static final Integer TRANSFROM = 13;
	public static final Integer OK = 14;
	public static final Integer EDITCONTENT = 15;
	public static final Integer PAGE = 16;
	public static final Integer NEXT = 17;
	public static final Integer PREV = 18;
	public static final Integer FIRST = 19;
	public static final Integer LAST = 20;
	public static final Integer SYLLABUS_CONTENT_EDIT = 21;
	public static final Integer EDIT_SECTION = 22;
	public static final Integer SELECT = 23;
	public static final Integer SAVE_TO_WEB = 24;
	public static final Integer PREVIEW = 25;
	public static final Integer VALUECHANGE = 26;
	public static final Integer UP = 27;
	public static final Integer DOWN = 28;
	public static final Integer SAVE_TO_TEMPLATE = 29;
	public static final Integer MESSAGE = 30;
	public static final Integer SUBMIT = 31;
	public static final Integer RESRTRICT = 32;
	public static final Integer NAVI = 33;
	public static final Integer INFO = 34;
	public static final Integer DOING = 35;
	public static final Integer CHECK_ERROR = 36;
	public static final Integer SAVE = 37;
	public static final Integer CHANGE_PWD = 38;
	public static final Integer CREATE = 39;
	public static final Integer SUCCESS = 40;
	public static final Integer FAIL = 41;
	public static final Integer DETAIL = 42;
	public static final Integer BIND_PRODUCT = 43;
	public static final Integer BIND_CLASSROOM = 44;
	public static final Integer LOGIN = 45;
	public static final Integer RETURN = 46;
	public static final Integer COLOR = 47;
	public static final Integer COLORS = 48;
	public static final Integer QUIT = 49;
	public static final Integer ENABLED = 50;
	public static final Integer SEARCH = 51;
	public static final Integer DBCLICK = 52;
	public static final Integer MODIFY = 53;

	public static final Integer CUSTOM = 3000;

}
