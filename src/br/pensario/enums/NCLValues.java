package br.pensario.enums;

public abstract class NCLValues {
	public enum NCLColor {
		gray, white, black, silver, red, maroon, fuchsia, purple, lime, green, yellow, olive, blue, navy, aqua, teal
	}
	
	public enum NCLQualifiers {
		par, seq, and, or
	}
	
	public enum NCLMimeTypes
	{
		text_html,
		text_plain,
		text_css,
		text_xml,
		image_bmp,
		image_png,
		image_gif,
		image_jpeg,
		audio_basic,
		audio_mp3,
		audio_mp2,
		audio_mpeg,
		audio_mpeg4,
		video_mpeg,
		application_x_ginga_NCL,
		application_x_ginga_NCLua,
		application_x_ginga_NCLet,
		application_x_ginga_settings,
		application_x_ginga_time
	}
	
	public enum NCLVariables 
	{
		system_language,
		system_caption,
		system_subtitle,
		system_returnBitRate,
		system_screenSize,
		system_screenGraphicSize,
		system_audioType,		
		system_devNumber,
		system_classType,
		system_info,
		system_classNumber,
		system_CPU,
		system_memory,
		system_operatingSystem,
		system_javaConfiguration,
		system_javaProfile,
		system_luaVersion,

		default_focusBorderColor,
		default_selBorderColor,
		default_focusBorderWidth,
		default_focusBorderTransparency,

		service_currentFocus,
		service_currentKeyMaster,

		si_numberOfServices,
		si_numberOfPartialServices,
		si_channelNumber,

		channel_keyCapture,
		channel_virtualKeyboard,
		channel_keyboardBounds
		
	}
}