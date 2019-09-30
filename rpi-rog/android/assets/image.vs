
attribute vec2 a_position;
attribute vec2 a_texCoord0;


uniform vec2 u_mouse_position;
varying vec2 v_texCoords;


void main ()
{
	gl_Position = vec4(a_position * vec2(1.0 / 1024.0 * 2.0, 1.0 / 768.0 * 2.0) - vec2(1, 1), 0, 1);
	v_texCoords = a_texCoord0;
}