
// attribute vec2 a_position;
attribute vec4 a_position;
attribute vec2 a_texCoord0;


uniform vec2 u_mouse_position;
varying vec2 v_texCoords;


void main ()
{
	gl_Position = a_position;
	v_texCoords = a_texCoord0;
}