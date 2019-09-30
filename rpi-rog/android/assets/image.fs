#ifdef GL_ES
	precision highp float;
#endif

uniform sampler2D u_image;
uniform vec2 u_mouse_position;

varying vec2 v_texCoords;

void main ()
{
	vec2 m = u_mouse_position;
	vec4 color = texture2D(u_image, v_texCoords);

	gl_FragColor = color;
}
